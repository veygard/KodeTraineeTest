package com.example.kodetraineetest.presentation.screens.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.kodetraineetest.presentation.model.UserParcelize
import com.example.kodetraineetest.presentation.model.toUser
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DetailScreen(
    navigator: DestinationsNavigator,
    userParcelize: UserParcelize
) {
    val user = userParcelize.toUser()
    Text(text = user.firstName?:"")
}