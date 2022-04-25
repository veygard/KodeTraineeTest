package com.example.kodetraineetest.navigation.xml

import android.content.Intent
import com.example.kodetraineetest.XmlActivity
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.presentation.screens.xml.screens.CriticalErrorFragment
import com.example.kodetraineetest.presentation.screens.xml.screens.MainScreenFragment
import com.example.kodetraineetest.presentation.screens.xml.screens.UserDetailsFragment
import com.example.kodetraineetest.presentation.screens.xml.widgets.UserListFragment
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun xmlStart() = ActivityScreen{
        Intent(it, XmlActivity::class.java)
    }

    fun mainScreen() = FragmentScreen {
        MainScreenFragment()
    }

    fun errorScreen() = FragmentScreen {
        CriticalErrorFragment()
    }

    fun detailScreen(user: User) = FragmentScreen {
        UserDetailsFragment.getNewInstance(user)
    }

    fun userList() = FragmentScreen{
        UserListFragment()
    }
}