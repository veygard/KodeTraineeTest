package com.example.kodetraineetest.navigation.xml

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kodetraineetest.R

interface UserDetailsScreenRouter {
    fun routeToMainScreen()
}

class UserDetailsScreenRouterImpl(
    private val fragment: Fragment
) : UserDetailsScreenRouter {


    override fun routeToMainScreen() {
        fragment.findNavController().navigate(R.id.action_userDetailsFragment_to_mainScreenFragment)
    }

}