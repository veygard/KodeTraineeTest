package com.example.kodetraineetest.di

import com.example.kodetraineetest.XmlActivity
import com.example.kodetraineetest.presentation.screens.xml.screens.CriticalErrorFragment
import com.example.kodetraineetest.presentation.screens.xml.screens.MainScreenFragment
import com.example.kodetraineetest.presentation.screens.xml.screens.UserDetailsFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [NavigationModule::class]
)
interface AppComponent {
    fun inject(activity: XmlActivity)

    fun inject(fragment: MainScreenFragment)
    fun inject(fragment: UserDetailsFragment)
    fun inject(fragment: CriticalErrorFragment)

}