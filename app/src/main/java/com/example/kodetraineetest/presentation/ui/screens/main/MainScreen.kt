package com.example.kodetraineetest.presentation.ui.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.presentation.ui.widgets.UserItemInList
import com.example.kodetraineetest.util.AvatarBank.getAvatarUrl
import com.example.kodetraineetest.util.Margin
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun MainScreen(
    
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = Margin.horizontalStandard, end = Margin.horizontalStandard)
    ) {
        LazyRow(content =)
    }
}