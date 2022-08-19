package com.hong.domain.sample.entity

import com.hong.base.domain.model.Entity

data class UserEntity(
    val avatar: String,
    val email: String,
    val firstName: String,
    val id: Int,
    val lastName: String
) : Entity