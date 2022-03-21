package com.example.kodetraineetest.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.presentation.screens.main.blocks.PositionTabRow
import com.example.kodetraineetest.presentation.screens.main.blocks.SearchBlock
import com.example.kodetraineetest.presentation.screens.main.blocks.UserListBlock
import com.example.kodetraineetest.presentation.ui.widgets.ShimmerUserList
import com.example.kodetraineetest.presentation.viewmodel.ScreenStates
import com.example.kodetraineetest.util.Margin
import com.example.kodetraineetest.util.SpacingVertical

@Composable
internal fun MainScreenContent(
    screenLoadingState: ScreenStates,
    userList: List<User>?,
    refreshClick: () -> Unit,
    sortByTabRow: (chosen: String, all: String) -> Unit,
    positionSet: Set<String>?,
    selectedTabIndex: MutableState<Int>,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(start = Margin.horizontalStandard, end = Margin.horizontalStandard)

    ) {
        SpacingVertical(heightDp = 6)
        SearchBlock()
        SpacingVertical(heightDp = 16)
        PositionTabRow(sortByTabRow, positionSet, selectedTabIndex)
        SpacingVertical(heightDp = 22)
        when (screenLoadingState) {
            is ScreenStates.Ready -> {
                userList?.let { list ->
                    UserListBlock(screenLoadingState, list, refreshClick)
                }
            }
            is ScreenStates.Loading -> {
                ShimmerUserList()
            }
        }
    }
}

