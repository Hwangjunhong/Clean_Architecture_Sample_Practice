package com.hong.data.common.di

import com.hong.data.common.utils.Constants.BASE_OKHTTP_CLIENT
import com.hong.data.common.utils.Constants.BASE_URL
import com.hong.data.common.utils.Constants.CONNECT_TIMEOUT
import com.hong.data.common.utils.Constants.GENERAL_AUTH
import com.hong.data.common.utils.Constants.READ_TIMEOUT
import com.hong.data.common.utils.Constants.WRITE_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNormalRetrofit(
        @Named(GENERAL_AUTH) okHttpClientBuilder: OkHttpClient.Builder
    ): Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClientBuilder.build())
        .build()

    @Provides
    @Singleton
    @Named(GENERAL_AUTH)
    fun provideGeneralAuthOkHttpClient(
        @Named(BASE_OKHTTP_CLIENT) baseOkHttpClientBuilder: OkHttpClient.Builder,
        @Named(GENERAL_AUTH) authInterceptor: Interceptor
    ): OkHttpClient.Builder = baseOkHttpClientBuilder
        .addInterceptor(authInterceptor)

    @Provides
    @Singleton
    @Named(BASE_OKHTTP_CLIENT)
    fun provideBaseOkHttpClient(loggerInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggerInterceptor)

}