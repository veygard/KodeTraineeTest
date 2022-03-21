package com.example.kodetraineetest.presentation.viewmodel

sealed class ScreenStates{
    object Loading: ScreenStates()
    object Ready: ScreenStates()
    object Error: ScreenStates()
}
