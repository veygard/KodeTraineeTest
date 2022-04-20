package com.example.kodetraineetest

import android.app.Application
import com.example.kodetraineetest.di.AppComponent
import com.example.kodetraineetest.di.DaggerAppComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application(){

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: MyApplication
    }

}