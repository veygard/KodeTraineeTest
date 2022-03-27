package com.example.kodetraineetest.presentation.common.supports

sealed class ScreenStates{
    object Loading: ScreenStates()
    object Ready: ScreenStates()
    object Error: ScreenStates()
}
