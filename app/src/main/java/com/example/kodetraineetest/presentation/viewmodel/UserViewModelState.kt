package com.example.kodetraineetest.presentation.viewmodel

import com.example.kodetraineetest.domain.model.User

sealed class UserViewModelState {
    data class GotUsers(val list: List<User>?) : UserViewModelState()
    data class ServerError(val error: String? = null) : UserViewModelState()
    data class ConnectionError(val error: String? = null) : UserViewModelState()
}
