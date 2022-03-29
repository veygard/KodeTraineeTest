package com.example.kodetraineetest.presentation.model

import com.example.kodetraineetest.domain.model.User

sealed class ScreenStates{
    object Loading: ScreenStates()
    data class Ready(val userList: List<User>? = null): ScreenStates()
    object Error: ScreenStates()
}
