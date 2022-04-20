package com.example.kodetraineetest.navigation.xml

import com.github.terrakok.cicerone.Router

interface CriticalErrorScreenRouter {
    fun routeToMainScreen()
}

class CriticalErrorScreenRouterImpl(
    private val router: Router
) : CriticalErrorScreenRouter {


    override fun routeToMainScreen() {
        router.navigateTo(Screens.mainScreen())
    }

}