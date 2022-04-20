package com.example.kodetraineetest.presentation.screens.xml.widgets

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.kodetraineetest.R
import com.example.kodetraineetest.databinding.BottomSheetSortBinding
import com.example.kodetraineetest.presentation.model.SortingTypes
import com.example.kodetraineetest.presentation.viewmodel.UsersViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SortBottomSheet : BottomSheetDialogFragment() {
    private var _binding: BottomSheetSortBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UsersViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.custom_bottom_sheet_theme)
    }

    private fun radioClickListener() {
        when(viewModel.sortedBy.value){
            SortingTypes.ABC ->   _binding?.radioGroup?.check( R.id.radio_abc)
            SortingTypes.BORN_DATE -> _binding?.radioGroup?.check( R.id.radio_date)
        }

        _binding?.radioGroup?.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_abc -> {
                    Log.d("sorting_type","sorting $checkedId ")
                    viewModel.sortByType(SortingTypes.ABC)
                    this.dismiss()
                }
                R.id.radio_date -> {
                    viewModel.sortByType(SortingTypes.BORN_DATE)
                    this.dismiss()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetSortBinding.inflate(inflater, container, false)
        radioClickListener()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "SortModalBottomSheet"
    }
}