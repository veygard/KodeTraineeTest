package com.example.kodetraineetest.presentation.screens.main.blocks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kodetraineetest.presentation.ui.theme.textMedium
import com.example.kodetraineetest.R
import kotlinx.coroutines.Job

@Composable
fun SearchBlock(sortButtonClick: () -> Job) {
    val showCancelButton = remember { mutableStateOf(false) }
    val enteredValue = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = enteredValue.value, onValueChange = {
                enteredValue.value = it
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = MaterialTheme.colors.primaryVariant,
                disabledBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            shape = RoundedCornerShape(24.dp),
            textStyle = textMedium,
            placeholder = {
                Text(
                    text = stringResource(R.string.search_field_placeholder),
                    color = MaterialTheme.colors.onSecondary,
                    style = textMedium
                )
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "search",
                    tint = if (enteredValue.value.isEmpty()) MaterialTheme.colors.onSecondary else MaterialTheme.colors.onBackground
                )
            },
            trailingIcon = {
                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .padding(end = 13.5.dp)
                        .clickable {
                            focusManager.clearFocus()
                            sortButtonClick()
                        },
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sort),
                        contentDescription = "sort",
                        tint = MaterialTheme.colors.onSecondary,
                    )

                }
            }
        )
    }
}
