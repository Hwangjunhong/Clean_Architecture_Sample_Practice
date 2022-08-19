package com.hong.domain.sample

import com.hong.domain.sample.entity.UserEntity
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface SampleRepository {
    fun getUserData(): Single<List<UserEntity>>
}