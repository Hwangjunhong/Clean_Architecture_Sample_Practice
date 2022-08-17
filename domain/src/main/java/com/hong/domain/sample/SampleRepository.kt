package com.hong.domain.sample

import com.hong.domain.sample.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface SampleRepository {
    suspend fun getUserData(): Flow<List<UserEntity>>
}