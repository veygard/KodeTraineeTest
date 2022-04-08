package com.example.kodetraineetest.presentation.screens.compose.screens.main.blocks

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.presentation.model.ScreenStates
import com.example.kodetraineetest.presentation.model.SortingTypes
import com.example.kodetraineetest.presentation.screens.compose.ui.theme.textMedium
import com.example.kodetraineetest.presentation.screens.compose.ui.widgets.NothingFoundBlock
import com.example.kodetraineetest.presentation.screens.compose.ui.widgets.UserItemInList
import com.example.kodetraineetest.util.Constants.CURRENT_YEAR
import com.example.kodetraineetest.util.Constants.NEXT_YEAR
import com.example.kodetraineetest.util.SpacingHorizontal
import com.example.kodetraineetest.util.SpacingVertical
import com.example.kodetraineetest.util.extention.toLocalDate
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.time.LocalDate

@ExperimentalFoundationApi
@Composable
fun UserListBlock(
    screenLoadingState: ScreenStates,
    list: List<User>,
    refreshClick: () -> Unit,
    sortedByState: SortingTypes,
    routeDetailScreen: (user: User) -> Unit,
) {

    val birthdayYearGroup: MutableState<Map<String, MutableSet<User>>> = remember {
        mutableStateOf(
            mapOf(
                CURRENT_YEAR to mutableSetOf(),
                NEXT_YEAR to mutableSetOf(),
            )
        )
    }
    val birthdayList = remember { mutableStateOf(listOf<User>()) }

    val isAnyUsers = remember { mutableStateOf(false) }


    isAnyUsers.value = isAnyUsersCheck(list)
    when (isAnyUsers.value) {
        true -> {
            SwipeRefresh(state = rememberSwipeRefreshState(
                screenLoadingState != ScreenStates.Ready()
            ), onRefresh = {
                refreshClick()
            }) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.background),
                    contentPadding = PaddingValues(vertical = 24.dp)
                ) {

                    when (sortedByState) {
                        SortingTypes.BORN_DATE -> {
                            birthdayYearGroup.value.forEach { (year, userList) ->
                                setupYearGroups(birthdayYearGroup, list)

                                if (year != CURRENT_YEAR && userList.isNotEmpty()) {
                                    stickyHeader {
                                        StickyHeader(year)
                                    }
                                }

                                birthdayList.value = sortByMonthDay(userList)

                                items(birthdayList.value, itemContent = { user ->
                                    UserItemInList(
                                        user = user,
                                        userClick = routeDetailScreen,
                                        showBornDate = true
                                    )
                                })
                            }
                        }

                        SortingTypes.ABC -> {
                            items(list.size) { index ->
                                UserItemInList(user = list[index], userClick = routeDetailScreen)
                                SpacingVertical(24)
                            }
                        }
                    }
                }
            }
        }

        false -> NothingFoundBlock(refreshClick)
    }

}

@Composable
private fun StickyHeader(year: String) {
    SpacingVertical(heightDp = 12)
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SpacingHorizontal(WidthDp = 8)
        Divider(
            color = MaterialTheme.colors.onSecondary,
            thickness = 1.dp,
            modifier = Modifier.width(72.dp)
        )
        Text(
            text = year,
            style = textMedium,
            color = MaterialTheme.colors.onSecondary
        )
        Divider(
            color = MaterialTheme.colors.onSecondary,
            thickness = 1.dp,
            modifier = Modifier.width(72.dp)
        )
        SpacingHorizontal(WidthDp = 8)
    }
    SpacingVertical(heightDp = 12)
}

private fun setupYearGroups(
    birthdayYearGroup: MutableState<Map<String, MutableSet<User>>>,
    list: List<User>
) {
    birthdayYearGroup.value.values.forEach {
        it.clear()
    }

    list.forEach { user ->
        val date = user.birthday?.toLocalDate()
        date?.let { d ->
            val day = d.dayOfMonth
            val month = d.month
            val year = LocalDate.now().year
            val newDate = LocalDate.of(year, month, day)

            if (newDate.isAfter(LocalDate.now())) {
                birthdayYearGroup.value[CURRENT_YEAR]?.add(user)
            } else {
                birthdayYearGroup.value[NEXT_YEAR]?.add(user)
            }
        }
    }

    birthdayYearGroup.value.values.forEach { set ->
        set.sortedWith(
            compareBy(
                { it.birthday?.toLocalDate()?.month },
                { it.birthday?.toLocalDate()?.dayOfMonth },
            )
        )
    }
}

private fun sortByMonthDay(userList: MutableSet<User>): List<User> {
    return userList.toList().sortedWith(
        compareBy(
            { it.birthday?.toLocalDate()?.month },
            { it.birthday?.toLocalDate()?.dayOfMonth },
        )
    )
}

private fun isAnyUsersCheck(list: List<User>) = list.isNotEmpty()