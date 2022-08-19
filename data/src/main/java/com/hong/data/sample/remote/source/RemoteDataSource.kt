package com.hong.data.sample.remote.source

import com.hong.data.model.UserDataModel
import io.reactivex.rxjava3.core.Single

interface RemoteDataSource {
    fun getUserData(): Single<List<UserDataModel>>
}