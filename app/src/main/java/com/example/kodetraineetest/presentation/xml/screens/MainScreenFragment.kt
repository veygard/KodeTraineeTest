package com.example.kodetraineetest.presentation.xml.screens

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.example.kodetraineetest.R
import com.example.kodetraineetest.databinding.FragmentMainScreenBinding
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.presentation.common.supports.ScreenStates
import com.example.kodetraineetest.presentation.xml.viewmodel.UsersViewModelXml
import com.example.kodetraineetest.presentation.xml.widgets.NothingFoundFragment
import com.example.kodetraineetest.presentation.xml.widgets.UserListFragment
import com.example.kodetraineetest.util.extention.onQueryTextChanged
import com.google.android.material.tabs.TabLayout


class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {
    private val viewModel: UsersViewModelXml by hiltNavGraphViewModels(R.id.xml_version_nav)
    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)

        tabLayoutListener()
        searchViewListener()
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getUsers()
        observeData(viewModel)

    }


    private fun observeData(viewModel: UsersViewModelXml) {
        var users = listOf<User>()
        viewModel.userListToShow.addObserver { result ->
            if (result != null) {
                users = result
                setListFragment(users)
            }
        }

        viewModel.screenLoadingState.addObserver { result ->
            when (result) {
                ScreenStates.Error -> criticalFragment()
                ScreenStates.Loading -> setNothingFoundFragment()
                ScreenStates.Ready -> setListFragment(users)
            }
        }

        viewModel.departmentsSet.addObserver { result ->
            if (result != null) {
                setTabs(result)
            }
        }
    }

    private fun searchViewListener() {
        _binding?.searchBar?.onQueryTextChanged { text ->
            viewModel.filterUsersBySearch(text)
            toggleSortButtonVisibility(text.isNotEmpty())
            toggleSearchViewIconColor(text.isNotEmpty())
        }
    }


    private fun setNothingFoundFragment() {
        val nestedFragment: Fragment = NothingFoundFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.list_container, nestedFragment).commit()
    }

    private fun tabLayoutListener() {
        _binding?.tabSlider?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.filterUsersByTabRow(
                    tab?.text.toString(),
                    context?.getString(R.string.department_tab_row_all) ?: ""
                )
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun criticalFragment() {
        val fm: FragmentManager = (context as FragmentActivity).supportFragmentManager
        val nestedFragment: Fragment = CriticalErrorFragment()
        fm
            .beginTransaction()
            .replace(R.id.fragmentContainerView, nestedFragment).commit()
    }

    private fun setListFragment(users: List<User>) {
        val nestedFragment: Fragment = UserListFragment(users)
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.list_container, nestedFragment).commit()
    }

    private fun setTabs(result: Set<String>) {
        _binding?.tabSlider?.removeAllTabs()
        result.forEach { tab ->
            _binding?.tabSlider?.newTab()?.let {
                it.text = tab
                _binding?.tabSlider?.addTab(it)
            }
        }
    }

    private fun toggleSortButtonVisibility(hide: Boolean) {
        if (hide) _binding?.sortButton?.visibility = View.GONE
        else _binding?.sortButton?.visibility = View.VISIBLE
    }

    private fun toggleSearchViewIconColor(isNotEmpty: Boolean) {
        val icon = _binding?.searchIcon

        if (isNotEmpty)  icon?.setColorFilter(
            context?.getColor(R.color.light_text_primary) ?: Color.BLACK
        )
        else icon?.setColorFilter(
            context?.getColor(R.color.light_content_default_secondary) ?: Color.LTGRAY
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}