package com.example.kodetraineetest.navigation.xml

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kodetraineetest.R

interface MainScreenRouter {
    fun routeToDetailScreen()
    fun routeToCriticalErrorScreen()
    fun openSortBottomSheet()

}

class MainScreenRouterImpl(
    private val fragment: Fragment
) : MainScreenRouter {

    override fun routeToDetailScreen() {
    }

    override fun routeToCriticalErrorScreen() {
        fragment.findNavController().navigate(R.id.action_mainScreenFragment_to_criticalErrorFragment)
    }

    override fun openSortBottomSheet() {
        fragment.findNavController().navigate(R.id.action_mainScreenFragment_to_sortBottomSheet)
    }

}