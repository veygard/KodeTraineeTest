package com.example.kodetraineetest.navigation.xml

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kodetraineetest.R
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.domain.model.toParcelize
import com.example.kodetraineetest.presentation.screens.xml.screens.UserDetailsFragment.Companion.USER

interface MainScreenRouter {
    fun routeToDetailScreen(user: User)
    fun routeToCriticalErrorScreen()
    fun openSortBottomSheet()

}

class MainScreenRouterImpl(
    private val fragment: Fragment
) : MainScreenRouter {

    override fun routeToDetailScreen(user: User) {
        fragment.findNavController().navigate(
            R.id.action_mainScreenFragment_to_userDetailsFragment,
            bundleOf(
                USER to user.toParcelize()
            )
        )
    }

    override fun routeToCriticalErrorScreen() {
        fragment.findNavController()
            .navigate(R.id.action_mainScreenFragment_to_criticalErrorFragment)
    }

    override fun openSortBottomSheet() {
        fragment.findNavController().navigate(R.id.action_mainScreenFragment_to_sortBottomSheet)
    }

}