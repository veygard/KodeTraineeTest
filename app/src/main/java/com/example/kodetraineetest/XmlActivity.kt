package com.example.kodetraineetest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kodetraineetest.navigation.xml.Screens
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class XmlActivity : AppCompatActivity(){

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator = object : AppNavigator(this, R.id.xml_container) {

        override fun applyCommands(commands: Array<out Command>) {
            super.applyCommands(commands)
            supportFragmentManager.executePendingTransactions()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApplication.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.xml_activity)

        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(Screens.mainScreen())))
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

}