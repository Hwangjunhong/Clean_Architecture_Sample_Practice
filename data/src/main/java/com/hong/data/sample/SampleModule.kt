package com.hong.data.sample

import com.hong.data.common.di.NetworkModule
import com.hong.data.sample.remote.api.SampleApi
import com.hong.data.sample.repository.SampleRepositoryImpl
import com.hong.domain.sample.SampleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class SampleModule {
    @Singleton
    @Provides
    fun provideSampleApi(retrofit: Retrofit): SampleApi {
        return retrofit.create(SampleApi::class.java)
    }

    @Singleton
    @Provides
    fun provideSampleRepository(sampleApi: SampleApi): SampleRepository {
        return SampleRepositoryImpl(sampleApi)
    }
}