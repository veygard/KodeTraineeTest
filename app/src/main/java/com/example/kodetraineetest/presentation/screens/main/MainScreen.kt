package com.example.kodetraineetest.presentation.screens.main

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kodetraineetest.presentation.viewmodel.ScreenStates
import com.example.kodetraineetest.presentation.ui.widgets.CriticalErrorBlock
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
    val selectedPositionTabIndex = remember { mutableStateOf(0) }

    LaunchedEffect(key1 = true, block = {
        viewModel.getUsers()
    })

    val refreshClick = {
        selectedPositionTabIndex.value = 0
        viewModel.refresh()
    }

    when (screenLoadingState) {
        is ScreenStates.Ready, ScreenStates.Loading -> {
            MainScreenContent(
                screenLoadingState,
                listToShow,
                refreshClick = refreshClick,
                sortByTabRow = { tab, all ->
                    viewModel.sortByTabRow(tab, all)
                },
                positionSet,
                selectedPositionTabIndex
            )
        }
        is ScreenStates.Error -> {
            CriticalErrorBlock(
                tryAgainClick = refreshClick
            )
        }
    }
}


