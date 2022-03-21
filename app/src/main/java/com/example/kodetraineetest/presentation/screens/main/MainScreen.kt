package com.example.kodetraineetest.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kodetraineetest.presentation.viewmodel.ScreenStates
import com.example.kodetraineetest.presentation.ui.widgets.CriticalErrorBlock
import com.example.kodetraineetest.presentation.ui.widgets.ShimmerUserList
import com.example.kodetraineetest.presentation.viewmodel.UsersViewModel
import com.example.kodetraineetest.util.Margin
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(start = Margin.horizontalStandard, end = Margin.horizontalStandard)
    ) {

        when (screenLoadingState) {
            is ScreenStates.Ready -> {
                listToShow?.let { list ->
                    MainScreenContent(
                        screenLoadingState,
                        list,
                        refreshClick = { viewModel.refresh() },
                        sortByTabRow = { tab, all ->
                            viewModel.sortByTabRow(tab, all)
                        },
                        positionSet
                    )
                } ?: run {
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


