package com.hong.presentation.model

import android.os.Parcelable
import com.hong.base.presentation.mapper.PresentationModelMapper
import com.hong.base.presentation.model.PresentationModel
import com.hong.domain.sample.entity.UserEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    val avatar: String,
    val email: String,
    val firstName: String,
    val id: Int,
    val lastName: String
) : Parcelable, PresentationModel {
    companion object : PresentationModelMapper<UserEntity, UserData> {
        override fun toPresentationModel(entity: UserEntity) = with(entity) {
            UserData(
                id = id,
                avatar = avatar,
                firstName = firstName,
                lastName = lastName,
                email = email
            )
        }
    }
}

