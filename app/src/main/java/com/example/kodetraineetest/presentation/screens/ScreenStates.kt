package com.example.kodetraineetest.presentation.screens

sealed class ScreenStates{
    object Loading:ScreenStates()
    object Ready:ScreenStates()
    object Error:ScreenStates()
}
