package com.example.kodetraineetest.presentation.common.model

import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.util.AvatarBank
import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize

@Parcelize
data class UserParcelize(
    val id: String? = null,
    val avatarUrl: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val userTag: String? = null,
    val department: String? = null,
    val position: String? = null,
    val birthday: String? = null,
    val phone: String? = null
): Parcelable

fun UserParcelize.toUser() = User(
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