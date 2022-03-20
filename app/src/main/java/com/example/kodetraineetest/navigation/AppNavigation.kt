package com.example.kodetraineetest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.kodetraineetest.presentation.ui.screens.main.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost


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