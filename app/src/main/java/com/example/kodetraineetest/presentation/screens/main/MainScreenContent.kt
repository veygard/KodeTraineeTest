package com.example.kodetraineetest.presentation.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.presentation.screens.main.blocks.PositionTabRow
import com.example.kodetraineetest.presentation.screens.main.blocks.UserListBlock
import com.example.kodetraineetest.presentation.viewmodel.ScreenStates
import com.example.kodetraineetest.util.SpacingVertical

@Composable
internal fun MainScreenContent(
    screenLoadingState: ScreenStates,
    userList: List<User>,
    refreshClick: () -> Unit,
    sortByTabRow: (chosen: String, all: String) -> Unit,
    positionSet: Set<String>?,
) {


    Column(modifier = Modifier.fillMaxSize()) {
        SpacingVertical(heightDp = 6)
        PositionTabRow(sortByTabRow, positionSet)
        SpacingVertical(heightDp = 22)
        UserListBlock(screenLoadingState, userList, refreshClick)
    }
}
