package com.example.kodetraineetest.presentation.screens.compose.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kodetraineetest.R
import com.example.kodetraineetest.presentation.model.SnackbarTypes
import com.example.kodetraineetest.presentation.screens.compose.ui.theme.subheadMedium

@ExperimentalMaterialApi
@Composable
fun RefreshingShackBar(bottomSheetScaffoldState: BottomSheetScaffoldState, snackbarState: SnackbarTypes?){
    var text= ""
    var background = Color.DarkGray
    var textColor= Color.Green

        when(snackbarState){
        SnackbarTypes.ConnectionError, SnackbarTypes.ServerError -> {
            text= stringResource(R.string.snackbar_errow_message)
            background= MaterialTheme.colors.error
            textColor= MaterialTheme.colors.onError
        }
        SnackbarTypes.Loading -> {
            text= stringResource(R.string.snackbar_loading_message)
            background= MaterialTheme.colors.primary
            textColor= MaterialTheme.colors.onError
        }
    }
    SnackbarHost(
        hostState = bottomSheetScaffoldState.snackbarHostState,
        snackbar = {
            Snackbar(
                modifier = Modifier
                    .padding(16.dp)
                ,
                backgroundColor = background,
                content = {
                    Text(
                        text = text,
                        style = subheadMedium,
                        color = textColor
                    )
                },
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Bottom)
    )
}