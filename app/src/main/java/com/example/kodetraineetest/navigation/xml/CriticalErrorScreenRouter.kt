package com.example.kodetraineetest.navigation.xml

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kodetraineetest.R

interface CriticalErrorScreenRouter {
    fun routeToMainScreen()
}

class CriticalErrorScreenRouterImpl(
    private val fragment: Fragment
) : CriticalErrorScreenRouter {


    override fun routeToMainScreen() {
        fragment.findNavController().navigate(R.id.action_criticalErrorFragment_to_mainScreenFragment3)
    }

}