package com.hong.data.common.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferenceModule {

    internal const val API = "Api"
    internal const val CACHING = "Caching"

    //    internal const val CUSTOM_ACCESS_TOKEN = "customAccessToken"
//    internal const val CUSTOM_REFRESH_TOKEN = "customRefreshToken"
    internal const val ACCESS_TOKEN = "accessToken"
    internal const val REFRESH_TOKEN = "refreshToken"

    @ApiSharedPreference
    @Provides
    fun provideApiSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(API, Context.MODE_PRIVATE)

    @CachingSharedPreference
    @Provides
    fun provideCachingSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(CACHING, Context.MODE_PRIVATE)
}