package com.example.kodetraineetest.navigation.xml

import com.example.kodetraineetest.domain.model.User
import com.github.terrakok.cicerone.Router

interface MainScreenRouter {
    fun routeToDetailScreen(user: User)
    fun routeToCriticalErrorScreen()
    fun openSortBottomSheet()
}

class MainScreenRouterImpl(
    private val router: Router
) : MainScreenRouter {

    override fun routeToDetailScreen(user: User) {
        router.navigateTo(Screens.errorScreen())
    }

    override fun routeToCriticalErrorScreen() {
        router.navigateTo(Screens.errorScreen())
    }

    override fun openSortBottomSheet() {

    }

}