package com.example.kodetraineetest.domain.model

import com.example.kodetraineetest.presentation.model.UserParcelize
import com.example.kodetraineetest.util.AvatarBank

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

fun User.toParcelize() = UserParcelize(
    id = id,
    avatarUrl = AvatarBank.getAvatarUrl(),
    firstName = firstName,
    lastName = lastName,
    userTag = userTag,
    department = department,
    position = position,
    birthday = birthday,
    phone = phone
)

