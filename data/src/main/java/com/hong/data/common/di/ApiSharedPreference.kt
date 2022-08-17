package com.hong.data.common.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiSharedPreference

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CachingSharedPreference