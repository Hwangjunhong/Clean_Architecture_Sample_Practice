package com.hong.domain.sample.usecase

import com.hong.domain.sample.SampleRepository
import com.hong.domain.sample.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val sampleRepository: SampleRepository
) {
    suspend fun invoke(): Flow<List<UserEntity>> {
        return sampleRepository.getUserData()
    }
}