package com.example.kodetraineetest.navigation.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.kodetraineetest.presentation.screens.compose.screens.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost


@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun AppNavigation(
    navController: NavHostController,
) {
    DestinationsNavHost(
        navGraph = NavGraphs.root,
        navController = navController,
    ) {
        /*Открывает экран который указан как стартовый.
        MainScreen в нашем случае
        В поиске: @Destination(start = true)
        */
    }
}