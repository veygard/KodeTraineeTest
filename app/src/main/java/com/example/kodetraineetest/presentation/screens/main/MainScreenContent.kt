package com.example.kodetraineetest.presentation.screens.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.presentation.screens.main.blocks.BottomSheetContent
import com.example.kodetraineetest.presentation.screens.main.blocks.DepartmentsTabRow
import com.example.kodetraineetest.presentation.screens.main.blocks.SearchBlock
import com.example.kodetraineetest.presentation.screens.main.blocks.UserListBlock
import com.example.kodetraineetest.presentation.ui.widgets.RefreshingShackBar
import com.example.kodetraineetest.presentation.ui.widgets.ShimmerUserList
import com.example.kodetraineetest.presentation.viewmodel.supports.ScreenStates
import com.example.kodetraineetest.presentation.viewmodel.supports.SnackbarTypes
import com.example.kodetraineetest.presentation.viewmodel.supports.SortingTypes
import com.example.kodetraineetest.util.SpacingVertical
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
internal fun MainScreenContent(
    screenLoadingState: ScreenStates,
    userList: List<User>?,
    refreshClick: () -> Unit,
    saveTabIndex: (position: Int) -> Unit,
    sortByTypeClick: (type: SortingTypes) -> Unit,
    sortBySearchEntered: (value: String) -> Unit,
    sortByTabRow: (chosen: String, all: String) -> Unit,
    routeDetailScreen: (user: User) -> Unit,
    departmentsSet: Set<String>?,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    sortButtonClick: () -> Job,
    coroutineScope: CoroutineScope,
    sortedByState: SortingTypes,
    enteredSearchValue: MutableState<String>,
    showCancelButton: MutableState<Boolean>,
    searchCancelButtonClick: () -> Unit,
    snackbarState: SnackbarTypes?,
    selectedPositionTabIndex: Int,
) {

    BottomSheetScaffold(
        sheetContent = {
            BottomSheetContent(sortByTypeClick, sortedByState)
            BackHandler(enabled = true) {
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                }
            }
        },
        sheetPeekHeight = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(start = 8.dp, end = 8.dp),
        sheetElevation = 8.dp,
        scaffoldState = bottomSheetScaffoldState,
        snackbarHost = {
            RefreshingShackBar(bottomSheetScaffoldState, snackbarState)
        },
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                SpacingVertical(heightDp = 6)
                SearchBlock(
                    sortButtonClick,
                    sortBySearchEntered,
                    enteredSearchValue,
                    showCancelButton,
                    searchCancelButtonClick
                )
                SpacingVertical(heightDp = 16)
                DepartmentsTabRow(sortByTabRow, departmentsSet, saveTabIndex, selectedPositionTabIndex)
                SpacingVertical(heightDp = 22)
                when (screenLoadingState) {
                    is ScreenStates.Ready -> {
                        userList?.let { list ->
                            UserListBlock(
                                screenLoadingState,
                                list,
                                refreshClick,
                                sortedByState,
                                routeDetailScreen
                            )
                        }
                    }
                    is ScreenStates.Loading -> {
                        ShimmerUserList()
                    }
                }
            }
        }
    )
}

