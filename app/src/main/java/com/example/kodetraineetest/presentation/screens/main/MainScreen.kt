package com.example.kodetraineetest.presentation.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.presentation.screens.ScreenStates
import com.example.kodetraineetest.presentation.screens.main.destinations.MainScreenDestination
import com.example.kodetraineetest.presentation.ui.widgets.CriticalErrorBlock
import com.example.kodetraineetest.presentation.ui.widgets.ShimmerUserList
import com.example.kodetraineetest.presentation.ui.widgets.UserItemInList
import com.example.kodetraineetest.presentation.viewmodel.UserViewModelState
import com.example.kodetraineetest.presentation.viewmodel.UsersViewModel
import com.example.kodetraineetest.util.Margin
import com.example.kodetraineetest.util.SpacingVertical
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
    val showScreen: MutableState<ScreenStates> = remember { mutableStateOf(ScreenStates.Loading) }


    LaunchedEffect(key1 = true, block = {
        viewModel.getUsers()
    })

    observeData(userStateCompose, userListToShown, showScreen)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = Margin.horizontalStandard, end = Margin.horizontalStandard)
    ) {

        when (showScreen.value) {
            is ScreenStates.Ready -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 24.dp)
                ) {
                    items(userListToShown.size) { index ->
                        UserItemInList(user = userListToShown[index], userClick = {})
                        SpacingVertical(24)
                    }
                }
            }
            is ScreenStates.Error -> {
                CriticalErrorBlock(
                    tryAgainClick = {
                        navigator.navigate(MainScreenDestination)
                    }
                )
            }

            is ScreenStates.Loading ->  {
                ShimmerUserList()
            }
        }
    }
}

private fun observeData(
    userStateCompose: UserViewModelState?,
    userListToShown: SnapshotStateList<User>,
    showScreen: MutableState<ScreenStates>
) {
    when (userStateCompose) {
        is UserViewModelState.ConnectionError -> {
            showScreen.value = ScreenStates.Error
        }
        is UserViewModelState.GotUsers -> {
            userStateCompose.list?.forEach {
                userListToShown.add(it)
            }
            showScreen.value = ScreenStates.Ready
        }
        is UserViewModelState.ServerError -> {
            showScreen.value = ScreenStates.Error
        }
    }
}

