package com.example.kodetraineetest.domain.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: String? = null,
    val avatarUrl: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val userTag: String? = null,
    val department: String? = null,
    val position: String? = null,
    val birthday: String? = null,
    val phone: String? = null
)
