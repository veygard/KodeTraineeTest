package com.example.kodetraineetest.presentation.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.presentation.screens.critical.CriticalErrorBlock
import com.example.kodetraineetest.presentation.ui.widgets.CircleProgressBar
import com.example.kodetraineetest.presentation.ui.widgets.LoadingBlock
import com.example.kodetraineetest.presentation.ui.widgets.UserItemInList
import com.example.kodetraineetest.presentation.viewmodel.UserViewModelState
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
    val userStateCompose by viewModel.userState.collectAsState()
    val userListToShown = remember { mutableStateListOf<User>() }
    val showScreen: MutableState<Boolean?> = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true, block = {
        viewModel.loadingShow()
        viewModel.getUsers()
    })

    observeData(userStateCompose, viewModel, userListToShown, showScreen)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = Margin.horizontalStandard, end = Margin.horizontalStandard)
    ) {
        CircleProgressBar(viewModel.loadingState)
        when (showScreen.value) {
            true -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    items(userListToShown.size) { index ->
                        UserItemInList(user = userListToShown[index], userClick = {})
                    }
                }
            }
            null -> {
                CriticalErrorBlock(
                    tryAgain = {
                        viewModel.loadingShow()
                        viewModel.getUsers()
                    }
                )
            }
            false -> {}
        }
    }
}

private fun observeData(
    userStateCompose: UserViewModelState?,
    viewModel: UsersViewModel,
    userListToShown: SnapshotStateList<User>,
    showScreen: MutableState<Boolean?>
) {
    when (userStateCompose) {
        is UserViewModelState.ConnectionError -> {
            viewModel.loadingHide()
            showScreen.value = null
        }
        is UserViewModelState.GotUsers -> {
            userStateCompose.list?.forEach {
                userListToShown.add(it)
            }
            showScreen.value = true
            viewModel.loadingHide()
        }
        is UserViewModelState.ServerError -> {
            viewModel.loadingHide()
            showScreen.value = null
        }
    }
}
