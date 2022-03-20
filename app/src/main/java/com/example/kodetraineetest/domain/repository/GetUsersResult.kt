package com.example.kodetraineetest.domain.repository

import com.example.kodetraineetest.domain.model.User

sealed class GetUsersResult(open val error: String? = null) {
    data class UserList(val list: List<User>) : GetUsersResult()
    data class ConnectionError(override val error: String? = null) : GetUsersResult(error = error)
    data class ServerError(override val error: String? = null) : GetUsersResult(error = error)
    data class EnqueueError(override val error: String? = null) : GetUsersResult(error = error)
}
