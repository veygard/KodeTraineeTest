package com.example.kodetraineetest

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kodetraineetest.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.main_activity) {


    private val binding: MainActivityBinding by viewBinding(R.id.const_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun composeButtonClick(@Suppress("UNUSED_PARAMETER") view: View) {
        startActivity(Intent(this, ComposeActivity::class.java))
    }

    fun xmlButtonClick(@Suppress("UNUSED_PARAMETER") view: View) {
        startActivity(Intent(this, XmlActivity::class.java))
    }
}
