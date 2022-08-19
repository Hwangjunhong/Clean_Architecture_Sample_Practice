package com.hong.domain.sample.usecase

import com.hong.domain.sample.SampleRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val sampleRepository: SampleRepository
) {
    operator fun invoke() = sampleRepository.getUserData()
}