package com.example.kodetraineetest.presentation.screens.main.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.presentation.ui.widgets.UserItemInList
import com.example.kodetraineetest.presentation.viewmodel.ScreenStates
import com.example.kodetraineetest.util.SpacingVertical
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun UserListBlock(
    screenLoadingState: ScreenStates,
    list: List<User>,
    refreshClick: () -> Unit
) {
    SwipeRefresh(state = rememberSwipeRefreshState(
        screenLoadingState != ScreenStates.Ready
    ), onRefresh = {
       refreshClick()
    }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background),
            contentPadding = PaddingValues(vertical = 24.dp)
        ) {
            items(list.size) { index ->
                UserItemInList(user = list[index], userClick = {})
                SpacingVertical(24)
            }
        }
    }

}