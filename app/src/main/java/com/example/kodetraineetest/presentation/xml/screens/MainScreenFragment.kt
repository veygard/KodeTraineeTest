package com.example.kodetraineetest.presentation.xml.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kodetraineetest.R
import com.example.kodetraineetest.databinding.FragmentMainScreenBinding
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.presentation.common.supports.ScreenStates
import com.example.kodetraineetest.presentation.xml.viewmodel.UsersViewModelXml
import com.example.kodetraineetest.presentation.xml.widgets.NothingFoundFragment
import com.example.kodetraineetest.presentation.xml.widgets.UserListFragment


class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {
    private val binding: FragmentMainScreenBinding by viewBinding(createMethod = CreateMethod.INFLATE)
    private val viewModel: UsersViewModelXml by hiltNavGraphViewModels(R.id.xml_version_nav)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_screen, container, false)

        setNothingFoundFragment()

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getUsers()
        observeData(viewModel)
    }

    private fun observeData(viewModel: UsersViewModelXml) {
        var users = listOf<User>()
        viewModel.userListToShow.addObserver { result->
            if(result != null){
            users = result
            }
        }

        viewModel.screenLoadingState.addObserver { result->
            when(result){
                ScreenStates.Error -> criticalFragment()
                ScreenStates.Loading -> setNothingFoundFragment()
                ScreenStates.Ready -> setListFragment(users)
            }

        }
    }

    private fun setNothingFoundFragment() {
        val nestedFragment: Fragment = NothingFoundFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.list_container, nestedFragment).commit()
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
}