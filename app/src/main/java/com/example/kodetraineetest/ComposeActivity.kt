package com.example.kodetraineetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kodetraineetest.navigation.compose.AppNavigation
import com.example.kodetraineetest.presentation.screens.compose.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class ComposeActivity  : ComponentActivity(){
    private lateinit var navController: NavHostController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            AppTheme {
                navController = rememberNavController()
                AppNavigation(navController= navController)
            }
        }
    }

}