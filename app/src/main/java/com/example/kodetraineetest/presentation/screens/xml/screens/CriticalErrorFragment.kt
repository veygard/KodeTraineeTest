package com.example.kodetraineetest.presentation.screens.xml.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kodetraineetest.databinding.FragmentCriticalErrorBinding
import com.example.kodetraineetest.navigation.xml.CriticalErrorScreenRouter
import com.example.kodetraineetest.navigation.xml.CriticalErrorScreenRouterImpl

class CriticalErrorFragment : Fragment() {
    private var _binding: FragmentCriticalErrorBinding? = null
    private val binding get() = _binding!!

    private val router: CriticalErrorScreenRouter by lazy {
        CriticalErrorScreenRouterImpl(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCriticalErrorBinding.inflate(inflater, container, false)

        reconnectButtonListener()
        return binding.root
    }

    private fun reconnectButtonListener() {
        _binding?.reconnectButton?.setOnClickListener {
            router.routeToMainScreen()
        }
    }


}