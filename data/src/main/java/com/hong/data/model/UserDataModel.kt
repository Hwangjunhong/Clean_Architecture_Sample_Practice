package com.hong.data.model

import android.os.Parcelable
import com.hong.base.data.model.DataModel
import com.hong.base.domain.mapper.EntityMapper
import com.hong.domain.sample.entity.UserEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDataModel(
    val avatar: String,
    val email: String,
    val firstName: String,
    val id: Int,
    val lastName: String
) : Parcelable, DataModel {
    companion object : EntityMapper<UserDataModel, UserEntity> {
        override fun toEntity(dataModel: UserDataModel) = with(dataModel) {
            UserEntity(
                id = id,
                avatar = avatar,
                email = email,
                firstName = firstName,
                lastName = lastName
            )
        }
    }
}
