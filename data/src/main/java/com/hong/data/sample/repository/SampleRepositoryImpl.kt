package com.hong.data.sample.repository

import com.hong.data.sample.remote.source.RemoteDataSource
import com.hong.data.model.UserDataModel
import com.hong.domain.sample.SampleRepository
import com.hong.domain.sample.entity.UserEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SampleRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
//    private val localDataSource: LocalDataSource
) : SampleRepository {

    override fun getUserData(): Single<List<UserEntity>> {
        return remoteDataSource
            .getUserData()
            .map { it.map(UserDataModel::toEntity) }
    }
}