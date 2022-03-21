package com.example.kodetraineetest.presentation.screens.main.blocks

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kodetraineetest.R
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.presentation.ui.theme.textMedium
import com.example.kodetraineetest.presentation.ui.theme.textSemibold


@Composable
fun PositionTabRow(
    sortByTabRow: (chosen: String, all: String) -> Unit,
    positionSet: Set<String>?
) {
    var selectedIndex by remember { mutableStateOf(0) }
    val allString = stringResource(R.string.detartment_tab_row_all)


    if (positionSet != null && positionSet.isNotEmpty()) {
        ScrollableTabRow(
            selectedTabIndex = selectedIndex,
            edgePadding = 0.dp,
            backgroundColor = MaterialTheme.colors.background,
            indicator = {
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(it[selectedIndex]),
                    height = 2.dp,
                    color = MaterialTheme.colors.primary
                )
            },
            tabs = {
                positionSet.forEachIndexed { index, tab ->
                    val textColor =
                        if (selectedIndex == index) MaterialTheme.colors.onBackground else MaterialTheme.colors.secondaryVariant
                    val textStyle = if (selectedIndex == index) textSemibold else textMedium
                    Tab(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            sortByTabRow(tab, allString)
                        },
                        text = {
                            Text(text = tab, color = textColor, style = textStyle)
                        })
                }
            }
        )
    }

}
