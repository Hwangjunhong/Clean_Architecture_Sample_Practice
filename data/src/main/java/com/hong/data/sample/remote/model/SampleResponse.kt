package com.hong.data.sample.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hong.base.data.mapper.DataMapper
import com.hong.data.model.UserDataModel
import kotlinx.parcelize.Parcelize
import me.hoyuo.gallery.base.data.model.remote.ResponseModel

@Parcelize
data class SampleResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val total_pages: Int
) : Parcelable

@Parcelize
data class Data(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_name")
    val lastName: String
) : Parcelable, ResponseModel {
    companion object : DataMapper<Data, UserDataModel> {
        override fun toDataModel(response: Data) = with(response) {
            UserDataModel(
                id = id,
                avatar = avatar,
                firstName = firstName,
                lastName = lastName,
                email = email
            )
        }
    }
}

