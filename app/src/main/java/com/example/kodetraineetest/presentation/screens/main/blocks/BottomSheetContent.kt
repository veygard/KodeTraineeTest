package com.example.kodetraineetest.presentation.screens.main.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kodetraineetest.R
import com.example.kodetraineetest.presentation.ui.theme.title2SemiBold
import com.example.kodetraineetest.util.SpacingVertical

@Composable
fun BottomSheetContent() {
    Column(
        modifier = Modifier

            .fillMaxWidth()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpacingVertical(heightDp = 24)
        Text(
            text = stringResource(R.string.bottom_sheet_sort_title),
            style = title2SemiBold, color = MaterialTheme.colors.onBackground
        )
        SpacingVertical(heightDp = 36)
        SpacingVertical(heightDp = 55)
    }
}

