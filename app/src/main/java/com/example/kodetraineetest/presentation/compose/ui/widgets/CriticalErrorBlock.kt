package com.example.kodetraineetest.presentation.compose.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kodetraineetest.R
import com.example.kodetraineetest.presentation.compose.ui.theme.headlineMedium
import com.example.kodetraineetest.presentation.compose.ui.theme.headlineRegular
import com.example.kodetraineetest.presentation.compose.ui.theme.title3SemiBold
import com.example.kodetraineetest.util.SpacingVertical

@Composable
fun CriticalErrorBlock(
    tryAgainClick:(()->Unit)? = null
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.flyingsaucer1f6f8),
                contentDescription = "error",
                modifier = Modifier.size(56.dp)
            )
            SpacingVertical(heightDp = 8)
            Text(
                text = stringResource(R.string.critical_error_screen_title),
                style = title3SemiBold,
                color = MaterialTheme.colors.onBackground
            )
            SpacingVertical(heightDp = 12)
            Text(
                text = stringResource(R.string.critical_error_screen_text),
                style = headlineRegular,
                color = MaterialTheme.colors.secondaryVariant
            )
            SpacingVertical(heightDp = 12)
            Text(
                text = stringResource(R.string.critical_error_screen_again),
                style = headlineMedium,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.clickable {
                    tryAgainClick?.invoke()
                }
            )
        }
    }
}