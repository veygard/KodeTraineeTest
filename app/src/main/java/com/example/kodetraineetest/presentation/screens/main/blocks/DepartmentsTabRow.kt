package com.example.kodetraineetest.presentation.screens.main.blocks

import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kodetraineetest.R
import com.example.kodetraineetest.presentation.ui.theme.textMedium
import com.example.kodetraineetest.presentation.ui.theme.textSemibold


@Composable
fun DepartmentsTabRow(
    sortByTabRow: (chosen: String, all: String) -> Unit,
    departmentsSet: Set<String>?,
    saveTabIndex: (position: Int) -> Unit,
    selectedPositionTabIndex: Int,
) {

    val allString = stringResource(R.string.department_tab_row_all)

    if (departmentsSet != null && departmentsSet.isNotEmpty()) {
        ScrollableTabRow(
            selectedTabIndex = selectedPositionTabIndex,
            edgePadding = 0.dp,
            backgroundColor = MaterialTheme.colors.background,
            indicator = {
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(it[selectedPositionTabIndex]),
                    height = 2.dp,
                    color = MaterialTheme.colors.primary
                )
            },
            tabs = {
                departmentsSet.forEachIndexed { index, tab ->
                    val textColor =
                        if (selectedPositionTabIndex == index) MaterialTheme.colors.onBackground else MaterialTheme.colors.secondaryVariant
                    val textStyle = if (selectedPositionTabIndex == index) textSemibold else textMedium
                    Tab(
                        selected = selectedPositionTabIndex == index,
                        onClick = {
                            sortByTabRow(tab, allString)
                            saveTabIndex(index)
                        },
                        text = {
                            Text(text = tab, color = textColor, style = textStyle)
                        })
                }
            }
        )
    }

}
