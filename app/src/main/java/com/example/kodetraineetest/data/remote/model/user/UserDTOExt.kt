package com.example.kodetraineetest.data.remote.model.user

import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.util.AvatarBank

fun UserDTO.toDomain() = User(
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

fun List<UserDTO>.toDomainList()= this.map { it.toDomain() }