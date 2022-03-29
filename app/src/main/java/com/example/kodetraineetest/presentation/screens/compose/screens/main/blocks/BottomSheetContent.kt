package com.example.kodetraineetest.presentation.screens.compose.screens.main.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kodetraineetest.R
import com.example.kodetraineetest.presentation.model.SortingTypes
import com.example.kodetraineetest.presentation.screens.compose.ui.theme.headlineMedium
import com.example.kodetraineetest.presentation.screens.compose.ui.theme.title2SemiBold
import com.example.kodetraineetest.presentation.screens.compose.ui.widgets.RadioButtonCustom
import com.example.kodetraineetest.util.SpacingHorizontal
import com.example.kodetraineetest.util.SpacingVertical

@Composable
fun BottomSheetContent(sortByTypeClick: (type: SortingTypes) -> Unit, sortedByState: SortingTypes) {
    val abcType = stringResource(R.string.radio_button_abc)
    val bornDate = stringResource(R.string.radio_button_born_date)
    val radioOptions = listOf(abcType, bornDate)
    val selectedOption = remember { mutableStateOf(radioOptions.first()) }

    when(sortedByState){
        SortingTypes.ABC -> selectedOption.value = abcType
        SortingTypes.BORN_DATE -> selectedOption.value = bornDate
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(start = 18.dp, end = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpacingVertical(heightDp = 24)
        Text(
            text = stringResource(R.string.bottom_sheet_sort_title),
            style = title2SemiBold, color = MaterialTheme.colors.onBackground
        )
        SpacingVertical(heightDp = 36)
        radioOptions.forEach { text ->
            val onClick = {
                selectedOption.value = text
                sortByTypeClick(
                    when (text) {
                        abcType -> SortingTypes.ABC
                        bornDate -> SortingTypes.BORN_DATE
                        else -> SortingTypes.ABC
                    },
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                RadioButtonCustom(
                    selected = text == selectedOption.value,
                    onClick = onClick,
                )
                SpacingHorizontal(WidthDp = 14)
                Text(text = text, style = headlineMedium, color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.clickable {
                        onClick()
                    }
                )
            }
        }
        SpacingVertical(heightDp = 55)
    }
}

