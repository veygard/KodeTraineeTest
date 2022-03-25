package com.example.kodetraineetest.presentation.viewmodel.supports

sealed class SnackbarTypes{
    object ConnectionError: SnackbarTypes()
    object ServerError: SnackbarTypes()
    object Loading: SnackbarTypes()
}
