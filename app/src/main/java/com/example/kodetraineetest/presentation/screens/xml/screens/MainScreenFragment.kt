package com.example.kodetraineetest.presentation.screens.xml.screens

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kodetraineetest.R
import com.example.kodetraineetest.databinding.FragmentMainScreenBinding
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.navigation.xml.MainScreenRouter
import com.example.kodetraineetest.navigation.xml.MainScreenRouterImpl
import com.example.kodetraineetest.presentation.model.ScreenStates
import com.example.kodetraineetest.presentation.model.SnackbarTypes
import com.example.kodetraineetest.presentation.model.SortingTypes
import com.example.kodetraineetest.presentation.screens.xml.adapters.UserClickInterface
import com.example.kodetraineetest.presentation.screens.xml.widgets.*
import com.example.kodetraineetest.presentation.viewmodel.UsersViewModel
import com.example.kodetraineetest.util.extention.onQueryTextChanged
import com.github.terrakok.cicerone.Router
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenFragment @Inject constructor(
//    private val router: Router
) : Fragment(R.layout.fragment_main_screen), UserClickInterface {

    private val viewModel: UsersViewModel by activityViewModels()
    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    private val mainScreenRouter: MainScreenRouter by lazy {
        MainScreenRouterImpl(Router())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle_test", " MainScreenFragment  OnCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        Log.d("lifecycle_test", " MainScreenFragment  onCreateView: ${_binding?.root}")
        observeViewModelFields(viewModel)

        /*делаем запрос на список юзеров при первом запуске вью-модели*/
        if (viewModel.userListToShow.value == null) viewModel.getUsers()
        if (viewModel.xmlUserClickInterfaceImpl.value == null) viewModel.setClickInterface(this)
        tabLayoutListener()
        searchViewListener()
        swipeRefreshListener()
        cancelButtonListener()
        sortButtonListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("lifecycle_test", " MainScreenFragment  onViewCreated ${_binding?.root}")
    }

    private fun sortButtonListener() {
        _binding?.sortButton?.setOnClickListener {
            mainScreenRouter.openSortBottomSheet()
        }
    }

    private fun swipeRefreshListener() {
        _binding?.recyclerUserRefresh?.setOnRefreshListener {
            viewModel.refresh()
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
                viewModel.saveTabIndex(tab?.position ?: 0)
                viewModel.filterUsersByTabRow(
                    tab?.text.toString(),
                    context?.getString(R.string.department_tab_row_all) ?: ""
                )
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.d(
                    "lifecycle_test", " MainScreenFragment  tabLayoutListener:  " +
                            "userListToShow: ${viewModel.userListToShow.value?.size}, " +
                            "Tab: ${viewModel.selectedPositionTabIndex.value},  " +
                            "screenLoadingState: ${viewModel.screenLoadingState.value} " +
                            "sortedBy: ${viewModel.sortedBy.value}"
                )
            }
        })
    }

    private fun routeToCriticalFragment() {
        mainScreenRouter.routeToCriticalErrorScreen()
    }

    private fun setListFragment() {
        val nestedFragment: Fragment = UserListFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.list_container, nestedFragment).commit()
        if (_binding?.tabSlider?.visibility == View.INVISIBLE) toggleVisibility(
            false,
            _binding?.tabSlider
        )
    }

    private fun setListByBornTypeFragment() {
        val nestedFragment: Fragment = UserListByGroupFragment()
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
        /*todo т.к. вместо Лайфдаты используется Стейтфлоу, то вместо обычных обсерверов вот такая реализация*/

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userListToShow.collect { result ->
                    if (result != null) {
                        when {
                            result.isNotEmpty()
                                    && viewModel.screenLoadingState.value == ScreenStates.Ready() -> {
                                when (viewModel.sortedBy.value) {
                                    SortingTypes.ABC -> setListFragment()
                                    SortingTypes.BORN_DATE -> setListByBornTypeFragment()
                                }
                            }
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
                            //тут можно ничего не делать, т.к. сработает обсервер в userListToShow, когда список получит значения.
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

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.showSnackbar.collect { result ->
                    Log.d("toast", "viewModel.showSnackbar, result: $result")
                    when (result) {
                        SnackbarTypes.ConnectionError -> showToast(result)
                        SnackbarTypes.Loading -> showToast(result)
                        SnackbarTypes.ServerError -> showToast(result)
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.sortedBy.collect { result ->
                    when (result) {
                        SortingTypes.ABC -> {
                            if (viewModel.userListToShow.value?.isNotEmpty() == true) {
                                _binding?.sortButton?.setColorFilter(
                                    context?.getColor(R.color.light_content_default_secondary)
                                        ?: Color.LTGRAY
                                )
                                setListFragment()
                            }
                        }
                        SortingTypes.BORN_DATE -> {
                            _binding?.sortButton?.setColorFilter(
                                context?.getColor(R.color.primary) ?: Color.LTGRAY
                            )
                            setListByBornTypeFragment()
                        }
                    }
                }
            }
        }
    }

    private fun showToast(snackbarState: SnackbarTypes?) {
        var text = ""
        var background = Color.LTGRAY
        var textColor = Color.BLACK

        when (snackbarState) {
            SnackbarTypes.ConnectionError, SnackbarTypes.ServerError -> {
                text = getString(R.string.snackbar_errow_message)
                background = this.requireContext().getColor(R.color.error)
                textColor = this.requireContext().getColor(R.color.white)
            }
            SnackbarTypes.Loading -> {
                text = getString(R.string.snackbar_loading_message)
                background = this.requireContext().getColor(R.color.primary)
                textColor = this.requireContext().getColor(R.color.white)
            }
        }
        Log.d("toast", "show toast")
        CustomToast(this.requireContext(), text, background, textColor = textColor).show()

    }


    override fun onUserClick(user: User) {
        mainScreenRouter.routeToDetailScreen(user)
    }

    override fun onPause() {
        super.onPause()
        Log.d(
            "lifecycle_test", "MainScreenFragment onPause:  " +
                    "userListToShow: ${viewModel.userListToShow.value?.size}, " +
                    "Tab: ${viewModel.selectedPositionTabIndex.value},  " +
                    "screenLoadingState: ${viewModel.screenLoadingState.value}" +
                    "sortedBy: ${viewModel.sortedBy.value}" +
                    " binding: $_binding"
        )
    }

    override fun onResume() {
        super.onResume()
        _binding?.tabSlider?.getTabAt(viewModel.selectedPositionTabIndex.value)?.select()
        Log.d(
            "lifecycle_test", " MainScreenFragment onResume:  " +
                    "userListToShow: ${viewModel.userListToShow.value?.size}, " +
                    "Tab: ${viewModel.selectedPositionTabIndex.value},  " +
                    "screenLoadingState: ${viewModel.screenLoadingState.value} " +
                    "sortedBy: ${viewModel.sortedBy.value}" +
                    ", binding: ${_binding?.root}"
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("lifecycle_test", " MainScreenFragment onDestroyView ${_binding?.root}")
    }
}