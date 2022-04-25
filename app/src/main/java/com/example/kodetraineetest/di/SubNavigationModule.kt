package com.example.kodetraineetest.di

import com.example.kodetraineetest.navigation.xml.subnavigation.MainScreenCiceroneHolder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
object SubNavigationModule {

    @Provides
    @Singleton
    fun provideLocalNavigationHolder(): MainScreenCiceroneHolder = MainScreenCiceroneHolder()
}