package com.example.kodetraineetest.presentation.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kodetraineetest.presentation.viewmodel.ScreenStates
import com.example.kodetraineetest.presentation.ui.widgets.CriticalErrorBlock
import com.example.kodetraineetest.presentation.ui.widgets.ShimmerUserList
import com.example.kodetraineetest.presentation.ui.widgets.UserItemInList
import com.example.kodetraineetest.presentation.viewmodel.UsersViewModel
import com.example.kodetraineetest.util.Margin
import com.example.kodetraineetest.util.SpacingVertical
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun MainScreen(
    navigator: DestinationsNavigator,
    viewModel: UsersViewModel = hiltViewModel(),
) {
    val screenLoadingState by viewModel.screenLoadingState.collectAsState()
    val listToShow by viewModel.userListToShow.collectAsState()

    LaunchedEffect(key1 = true, block = {
        viewModel.getUsers()
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = Margin.horizontalStandard, end = Margin.horizontalStandard)
    ) {

        when (screenLoadingState) {
            is ScreenStates.Ready -> {
                listToShow?.let { list->
                    SwipeRefresh(state = rememberSwipeRefreshState(
                        screenLoadingState != ScreenStates.Ready
                    ), onRefresh = {
                        viewModel.refresh()
                    }) {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(vertical = 24.dp)
                        ) {
                            items(list.size) { index ->
                                UserItemInList(user = list[index], userClick = {})
                                SpacingVertical(24)
                            }
                        }
                    }
                } ?: run{
                    viewModel.loadingError()
                }
            }
            is ScreenStates.Error -> {
                CriticalErrorBlock(
                    tryAgainClick = {
                        viewModel.refresh()
                    }
                )
            }

            is ScreenStates.Loading -> {
                ShimmerUserList()
            }
        }
    }
}
