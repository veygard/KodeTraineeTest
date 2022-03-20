package com.example.kodetraineetest.presentation.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.kodetraineetest.presentation.viewmodel.LoadingState

@Composable
fun CircleProgressBar(loadingState: dev.icerock.moko.mvvm.livedata.LiveData<LoadingState>) {
    val show = remember { mutableStateOf(false)}
    loadingState.addObserver { result->
        when(result){
            LoadingState.Hide -> show.value = false
            LoadingState.Show -> show.value = true
        }
    }
    if(show.value) Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){CircularProgressIndicator()}
}