package com.example.kodetraineetest.presentation.common.supports

sealed class SnackbarTypes{
    object ConnectionError: SnackbarTypes()
    object ServerError: SnackbarTypes()
    object Loading: SnackbarTypes()
}
