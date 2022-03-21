package com.example.kodetraineetest.presentation.screens.main

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kodetraineetest.presentation.viewmodel.ScreenStates
import com.example.kodetraineetest.presentation.ui.widgets.CriticalErrorBlock
import com.example.kodetraineetest.presentation.ui.widgets.ShimmerUserList
import com.example.kodetraineetest.presentation.viewmodel.UsersViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun MainScreen(
    navigator: DestinationsNavigator,
    viewModel: UsersViewModel = hiltViewModel(),
) {
    val screenLoadingState by viewModel.screenLoadingState.collectAsState()
    val positionSet by viewModel.positionSet.collectAsState()
    val listToShow by viewModel.userListToShow.collectAsState()

    LaunchedEffect(key1 = true, block = {
        viewModel.getUsers()
    })


    when (screenLoadingState) {
        is ScreenStates.Ready, ScreenStates.Loading -> {
            MainScreenContent(
                screenLoadingState,
                listToShow,
                refreshClick = { viewModel.refresh() },
                sortByTabRow = { tab, all ->
                    viewModel.sortByTabRow(tab, all)
                },
                positionSet,
            )
        }
        is ScreenStates.Error -> {
            CriticalErrorBlock(
                tryAgainClick = {
                    viewModel.refresh()
                }
            )
        }
    }
}


