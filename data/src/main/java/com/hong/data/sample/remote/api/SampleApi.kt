package com.hong.data.sample.remote.api

import com.hong.data.sample.remote.dto.SampleResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface SampleApi {
    @GET("api/users")
    suspend fun sampleUserData(): SampleResponse
}