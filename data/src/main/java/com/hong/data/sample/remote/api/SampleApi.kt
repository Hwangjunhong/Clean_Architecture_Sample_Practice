package com.hong.data.sample.remote.api

import com.hong.data.sample.remote.model.SampleResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface SampleApi {
    @GET("api/users")
    fun sampleUserData(): Single<SampleResponse>
}