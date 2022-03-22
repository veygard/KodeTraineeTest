package com.example.kodetraineetest.presentation.screens.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kodetraineetest.presentation.viewmodel.supports.ScreenStates
import com.example.kodetraineetest.presentation.ui.widgets.CriticalErrorBlock
import com.example.kodetraineetest.presentation.viewmodel.UsersViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Destination(start = true)
@Composable
fun MainScreen(
    navigator: DestinationsNavigator,
    viewModel: UsersViewModel = hiltViewModel(),
) {
    val screenLoadingState by viewModel.screenLoadingState.collectAsState()
    val departmentsSet by viewModel.departmentsSet.collectAsState()
    val listToShow by viewModel.userListToShow.collectAsState()
    val sortedByState by viewModel.sortedBy.collectAsState()
    val selectedPositionTabIndex = remember { mutableStateOf(0) }

    val coroutineScope = rememberCoroutineScope()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )


    LaunchedEffect(key1 = true, block = {
        viewModel.getUsers()
    })


    val refreshClick = {
        selectedPositionTabIndex.value = 0
        viewModel.refresh()
        val i = 10
    }
    val sortButtonClick = {
        coroutineScope.launch {
            bottomSheetScaffoldState.bottomSheetState.expand()
        }
    }

    when (screenLoadingState) {
        is ScreenStates.Ready, ScreenStates.Loading -> {
            MainScreenContent(
                screenLoadingState,
                listToShow,
                refreshClick = refreshClick,
                sortByTypeClick = { type ->
                    viewModel.sortByType(type)
                },
                sortByTabRow = { tab, all ->
                    viewModel.sortByTabRow(tab, all)
                },
                departmentsSet,
                selectedPositionTabIndex,
                bottomSheetScaffoldState,
                sortButtonClick,
                coroutineScope,
                sortedByState,
            )
        }
        is ScreenStates.Error -> {
            CriticalErrorBlock(
                tryAgainClick = refreshClick
            )
        }
    }
}


