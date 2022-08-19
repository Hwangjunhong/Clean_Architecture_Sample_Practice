package com.hong.data.sample.remote.source

import com.hong.base.exception.ResponseExceptionHandler
import com.hong.data.model.UserDataModel
import com.hong.data.sample.remote.api.SampleApi
import com.hong.data.sample.remote.model.Data
import io.reactivex.rxjava3.core.Single

class RemoteDataSourceImpl constructor(
    private val sampleApi: SampleApi,
) : RemoteDataSource {
    override fun getUserData(): Single<List<UserDataModel>> {
        return sampleApi
            .sampleUserData()
            .map { it.data.map(Data::toDataModel) }
            .compose(ResponseExceptionHandler())
    }
}