package com.example.kodetraineetest.data.remote.model.user

import com.google.gson.annotations.SerializedName

data class GetUsersResponse(
    @SerializedName("items")
    val userList: List<UserDTO>
)