package com.example.kodetraineetest.presentation.screens.compose.screens.main.blocks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kodetraineetest.R
import com.example.kodetraineetest.presentation.screens.compose.ui.theme.subheadSemibold
import com.example.kodetraineetest.presentation.screens.compose.ui.theme.textMedium
import com.example.kodetraineetest.util.SpacingHorizontal
import kotlinx.coroutines.Job

@Composable
fun SearchBlock(
    sortButtonClick: () -> Job,
    sortBySearchEntered: (value: String) -> Unit,
    enteredValue: MutableState<String>,
    showCancelButton: MutableState<Boolean>,
    searchCancelButtonClick: () -> Unit
) {


    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        TextField(
            modifier = when (showCancelButton.value) {
                true -> Modifier
                false-> Modifier
                    .fillMaxWidth()
            },
            value = enteredValue.value, onValueChange = {
                enteredValue.value = it
                sortBySearchEntered(it)
                showCancelButton.value = it.isNotEmpty()
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

        if (showCancelButton.value) {
            SpacingHorizontal(12)
            Text(text = stringResource(R.string.cancel_button_title),
                style = subheadSemibold,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.clickable {
                    searchCancelButtonClick()
                }
            )
        }
    }
}
