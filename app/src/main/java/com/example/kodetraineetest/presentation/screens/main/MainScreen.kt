package com.example.kodetraineetest.presentation.screens.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kodetraineetest.domain.model.toParcelize
import com.example.kodetraineetest.presentation.screens.destinations.DetailScreenDestination
import com.example.kodetraineetest.presentation.ui.widgets.CriticalErrorBlock
import com.example.kodetraineetest.presentation.viewmodel.UsersViewModel
import com.example.kodetraineetest.presentation.viewmodel.supports.ScreenStates
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
    val shackbarState by viewModel.showSnackbar.collectAsState()
    val selectedPositionTabIndex by viewModel.selectedPositionTabIndex.collectAsState()




    val coroutineScope = rememberCoroutineScope()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val enteredSearchValue = remember { mutableStateOf("") }
    val showCancelButton = remember { mutableStateOf(false) }
    val searchCancelButtonClick = {
        enteredSearchValue.value = ""
        showCancelButton.value= false
        viewModel.filterUsersBySearch("")
    }

    LaunchedEffect(key1 = true, block = {
        viewModel.getUsers()
    })

    fun refreshClick(isLoadStateNeeded: Boolean = false) {
        enteredSearchValue.value = ""

        if(!isLoadStateNeeded) coroutineScope.launch {
            bottomSheetScaffoldState.snackbarHostState.showSnackbar("")
        }

        viewModel.refresh(isLoadStateNeeded)
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
                refreshClick = {refreshClick()},
                saveTabIndex = { p-> viewModel.saveTabIndex(p)},
                sortByTypeClick = { type ->
                    viewModel.sortByType(type)
                },
                sortBySearchEntered = { value ->
                    viewModel.filterUsersBySearch(value)
                },
                sortByTabRow = { tab, all ->
                    viewModel.filterUsersByTabRow(tab, all)
                },
                routeDetailScreen = {user ->
                    navigator.navigate(DetailScreenDestination(user.toParcelize()))
                },
                departmentsSet,
                bottomSheetScaffoldState,
                sortButtonClick,
                coroutineScope,
                sortedByState,
                enteredSearchValue,
                showCancelButton,
                searchCancelButtonClick,
                shackbarState,
                selectedPositionTabIndex
            )
        }
        is ScreenStates.Error -> {
            CriticalErrorBlock(
                tryAgainClick = {refreshClick(true)}
            )
        }
    }


}


