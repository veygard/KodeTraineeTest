package com.example.kodetraineetest.presentation.screens.xml.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.kodetraineetest.databinding.FragmentCriticalErrorBinding
import com.example.kodetraineetest.navigation.xml.CriticalErrorScreenRouter
import com.example.kodetraineetest.navigation.xml.CriticalErrorScreenRouterImpl
import com.example.kodetraineetest.presentation.viewmodel.UsersViewModel
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CriticalErrorFragment : Fragment() {
    private val viewModel: UsersViewModel by activityViewModels()
    private var _binding: FragmentCriticalErrorBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var router: Router

    private val routerCriticalErrorFragment: CriticalErrorScreenRouter by lazy {
        CriticalErrorScreenRouterImpl(router)
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
            viewModel.setLoading()
            routerCriticalErrorFragment.routeToMainScreen()
        }
    }


}