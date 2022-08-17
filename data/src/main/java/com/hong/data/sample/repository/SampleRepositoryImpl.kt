package com.hong.data.sample.repository

import com.hong.data.sample.remote.api.SampleApi
import com.hong.domain.sample.SampleRepository
import com.hong.domain.sample.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SampleRepositoryImpl @Inject constructor(
    private val sampleApi: SampleApi
) : SampleRepository {

    override suspend fun getUserData(): Flow<List<UserEntity>> {
        return flow {
            val response = sampleApi.sampleUserData()
            val userData = mutableListOf<UserEntity>()
            response.data.forEach { data ->
                userData.add(
                    UserEntity(
                        avatar = data.avatar,
                        email = data.email,
                        first_name = data.first_name,
                        id = data.id,
                        last_name = data.last_name
                    )
                )
            }
            emit(userData)
        }
    }


}