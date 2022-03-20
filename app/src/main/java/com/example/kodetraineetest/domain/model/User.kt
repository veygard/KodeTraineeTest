package com.example.kodetraineetest.domain.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: String,
    val avatarUrl: String,
    val firstName: String,
    val lastName: String,
    val userTag: String,
    val department: String,
    val position: String,
    val birthday: String,
    val phone: String
)
