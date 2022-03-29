package com.example.kodetraineetest.presentation.model

sealed class SnackbarTypes{
    object ConnectionError: SnackbarTypes()
    object ServerError: SnackbarTypes()
    object Loading: SnackbarTypes()
}
