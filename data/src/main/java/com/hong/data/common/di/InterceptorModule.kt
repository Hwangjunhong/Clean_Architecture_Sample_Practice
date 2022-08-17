package com.hong.data.common.di

import com.hong.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InterceptorModule {

    internal const val HEADER_CONTENT_TYPE = "Content-Type"
    internal const val APPLICATION_JSON = "application/json"
    internal const val X_AUTH_TOKEN = "x-auth-token"
    internal const val REMOTE_LOG_REGEX = "서버통신: %s"
    internal const val GENERAL_AUTH = "GeneralAuth"
    internal const val AUTHORIZATION = "Authorization"

    @Provides
    @Singleton
    fun providerHttpLoggingInterceptorLogger(): HttpLoggingInterceptor.Logger =
        HttpLoggingInterceptor.Logger { Timber.d(REMOTE_LOG_REGEX, it) }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(logger: HttpLoggingInterceptor.Logger): HttpLoggingInterceptor =
        HttpLoggingInterceptor(logger).apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    @Singleton
    @Named(GENERAL_AUTH)
    fun provideGeneralAuthInterceptor(): Interceptor = Interceptor { chain ->
        with(chain) {
            val newRequest = request().newBuilder().run {
                addHeader(HEADER_CONTENT_TYPE, APPLICATION_JSON)
//                when (accessToken.isNotEmpty()) {
//                    true -> addHeader(AUTHORIZATION, "Bearer $accessToken")
//                    false -> addHeader(AUTHORIZATION, accessToken)
//                }
                build()
            }
            proceed(newRequest)
        }
    }
}