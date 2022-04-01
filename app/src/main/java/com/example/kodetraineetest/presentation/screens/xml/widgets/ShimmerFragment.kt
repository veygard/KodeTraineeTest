package com.example.kodetraineetest.presentation.screens.xml.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kodetraineetest.R
import com.example.kodetraineetest.databinding.FragmentShimmerBinding

class ShimmerFragment: Fragment(R.layout.fragment_shimmer) {
    private var _binding: FragmentShimmerBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentShimmerBinding.inflate(inflater, container, false)
        _binding?.shimmerLayout?.startShimmer()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.shimmerLayout?.stopShimmer()
        _binding = null
    }

}