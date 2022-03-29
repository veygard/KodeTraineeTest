package com.example.kodetraineetest.presentation.screens.xml.screens

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kodetraineetest.R
import com.example.kodetraineetest.databinding.FragmentMainScreenBinding
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.navigation.xml.MainScreenRouter
import com.example.kodetraineetest.navigation.xml.MainScreenRouterImpl
import com.example.kodetraineetest.presentation.model.ScreenStates
import com.example.kodetraineetest.presentation.screens.xml.widgets.NothingFoundFragment
import com.example.kodetraineetest.presentation.screens.xml.widgets.ShimmerFragment
import com.example.kodetraineetest.presentation.screens.xml.widgets.UserListFragment
import com.example.kodetraineetest.presentation.viewmodel.UsersViewModel
import com.example.kodetraineetest.util.extention.onQueryTextChanged
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch


class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {
    private val viewModel: UsersViewModel by hiltNavGraphViewModels(R.id.xml_version_nav)
    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    private val mainScreenRouter: MainScreenRouter by lazy {
        MainScreenRouterImpl(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)

        observeViewModelFields(viewModel)
        viewModel.getUsers()
        tabLayoutListener()
        searchViewListener()
        swipeRefreshListener()
        cancelButtonListener()

        return binding.root
    }

    private fun swipeRefreshListener() {
        _binding?.recyclerUserRefresh?.setOnRefreshListener {
            viewModel.getUsers()
            _binding?.recyclerUserRefresh?.isRefreshing = false
        }
    }


    private fun searchViewListener() {
        _binding?.searchBar?.onQueryTextChanged { text ->
            viewModel.filterUsersBySearch(text)
            toggleVisibility(text.isNotEmpty(), _binding?.sortButton)
            toggleVisibility(text.isEmpty(), _binding?.cancelButton)
            toggleSearchViewIconColor(text.isNotEmpty())
        }
    }


    private fun setNothingFoundFragment() {
        val nestedFragment: Fragment = NothingFoundFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.list_container, nestedFragment).commit()
    }

    private fun setShimmerFragment() {
        val nestedFragment: Fragment = ShimmerFragment()
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

    private fun routeToCriticalFragment() {
        mainScreenRouter.routeToCriticalErrorScreen()
    }

    private fun setListFragment(users: List<User>) {
        val nestedFragment: Fragment = UserListFragment(users)
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.list_container, nestedFragment).commit()
        if (_binding?.tabSlider?.visibility == View.INVISIBLE) toggleVisibility(
            false,
            _binding?.tabSlider
        )
    }

    private fun setTabs(result: Set<String>) {
        val tabs = _binding?.tabSlider
        tabs?.removeAllTabs()
        result.forEach { tab ->
            tabs?.newTab()?.let {
                it.text = tab
                tabs.addTab(it)
            }
        }
    }

    private fun toggleVisibility(gone: Boolean, view: View?) {
        if (gone) view?.visibility = View.GONE
        else view?.visibility = View.VISIBLE
    }

    private fun toggleSearchViewIconColor(isNotEmpty: Boolean) {
        val icon = _binding?.searchIcon

        if (isNotEmpty) icon?.setColorFilter(
            context?.getColor(R.color.light_text_primary) ?: Color.BLACK
        )
        else icon?.setColorFilter(
            context?.getColor(R.color.light_content_default_secondary) ?: Color.LTGRAY
        )
    }

    private fun cancelButtonListener() {
        _binding?.cancelButton?.setOnClickListener {
            _binding?.searchBar?.setQuery("", false)
            _binding?.searchBar?.clearFocus()
        }
    }

    private fun observeViewModelFields(viewModel: UsersViewModel) {

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userListToShow.collect { result ->
                    if (result != null) {
                        when {
                            result.isNotEmpty() -> setListFragment(result)
                            result.isEmpty() -> setNothingFoundFragment()
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.screenLoadingState.collect { result ->
                    when (result) {
                        ScreenStates.Error -> {
                            routeToCriticalFragment()
                        }
                        ScreenStates.Loading -> {
                            setShimmerFragment()
                        }
                        is ScreenStates.Ready -> {
                            setListFragment(result.userList ?: emptyList())
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.departmentsSet.collect { result ->
                    if (result != null) {
                        setTabs(result)
                    }
                }
            }
        }

//        lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.showSnackbar.collect { result ->
//                    when(result){
//                        SnackbarTypes.ConnectionError -> TODO()
//                        SnackbarTypes.Loading -> TODO()
//                        SnackbarTypes.ServerError -> TODO()
//                    }
//                }
//            }
//        }
    }

//    private fun showToast(snackbarState: SnackbarTypes?){
//        var text= ""
//        var background = androidx.compose.ui.graphics.Color.DarkGray
//        var textColor= androidx.compose.ui.graphics.Color.Green
//
//        when(snackbarState){
//            SnackbarTypes.ConnectionError, SnackbarTypes.ServerError -> {
//                text= getString(R.string.snackbar_errow_message)
//                background= getColor
//                textColor= MaterialTheme.colors.onError
//            }
//            SnackbarTypes.Loading -> {
//                text= getString(R.string.snackbar_loading_message)
//                background= MaterialTheme.colors.primary
//                textColor= MaterialTheme.colors.onError
//            }
//        }
//    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}