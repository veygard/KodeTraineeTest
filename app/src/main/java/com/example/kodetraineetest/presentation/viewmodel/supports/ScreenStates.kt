package com.example.kodetraineetest.presentation.viewmodel.supports

sealed class ScreenStates{
    object Loading: ScreenStates()
    object Ready: ScreenStates()
    object Error: ScreenStates()
}
