package com.example.kodetraineetest.presentation.compose.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kodetraineetest.R
import com.example.kodetraineetest.presentation.compose.ui.theme.headlineRegular
import com.example.kodetraineetest.presentation.compose.ui.theme.title3SemiBold
import com.example.kodetraineetest.util.SpacingVertical

@Composable
fun NothingFoundBlock(refreshClick: () -> Unit) {
    val offsetX:MutableState<Float> = remember { mutableStateOf(0f)}
    val offsetY:MutableState<Float> = remember { mutableStateOf(0f)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consumeAllChanges()

                    val (x, y) = dragAmount
                    when {
                        y > 21-> {
                        refreshClick()
                        }
                    }

                    offsetX.value += dragAmount.x
                    offsetY.value += dragAmount.y
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SpacingVertical(80)
            Image(
                painter = painterResource(id = R.drawable.glass),
                contentDescription = "not found",
                modifier = Modifier.size(56.dp)
            )
            SpacingVertical(heightDp = 8)
            Text(
                text = stringResource(R.string.nothing_found_title),
                style = title3SemiBold,
                color = MaterialTheme.colors.onBackground
            )
            SpacingVertical(heightDp = 12)
            Text(
                text = stringResource(R.string.nothing_found_text),
                style = headlineRegular,
                color = MaterialTheme.colors.secondaryVariant
            )
            SpacingVertical(heightDp = 3)
        }
    }
}

