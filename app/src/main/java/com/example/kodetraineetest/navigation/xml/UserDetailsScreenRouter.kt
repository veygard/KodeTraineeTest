package com.example.kodetraineetest.navigation.xml

import com.github.terrakok.cicerone.Router

interface UserDetailsScreenRouter {
    fun routeToMainScreen()
}

class UserDetailsScreenRouterImpl(
    private val router: Router
) : UserDetailsScreenRouter {


    override fun routeToMainScreen() {
        router.exit()
    }

}