package com.example.kodetraineetest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kodetraineetest.databinding.XmlActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class XmlActivity : AppCompatActivity(R.layout.xml_activity){
    private val binding: XmlActivityBinding by viewBinding(R.id.xml_const_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}