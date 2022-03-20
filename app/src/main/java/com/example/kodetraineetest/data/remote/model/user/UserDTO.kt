package com.example.kodetraineetest.data.remote.model.user

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("avatarUrl")
    val avatarUrl: String? = null,
    @SerializedName("firstName")
    val firstName: String? = null,
    @SerializedName("lastName")
    val lastName: String? = null,
    @SerializedName("userTag")
    val userTag: String? = null,
    @SerializedName("department")
    val department: String? = null,
    @SerializedName("position")
    val position: String? = null,
    @SerializedName("birthday")
    val birthday: String? = null,
    @SerializedName("phone")
    val phone: String? = null
)
