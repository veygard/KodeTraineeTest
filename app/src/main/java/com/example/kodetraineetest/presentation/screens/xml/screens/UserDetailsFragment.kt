package com.example.kodetraineetest.presentation.screens.xml.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import com.example.kodetraineetest.R
import com.example.kodetraineetest.databinding.FragmentUserDetailsBinding
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.navigation.xml.UserDetailsScreenRouter
import com.example.kodetraineetest.navigation.xml.UserDetailsScreenRouterImpl
import com.example.kodetraineetest.presentation.model.UserParcelize
import com.example.kodetraineetest.presentation.model.toUser
import com.example.kodetraineetest.util.ageDescription
import com.example.kodetraineetest.util.extention.formatPhone
import com.example.kodetraineetest.util.extention.formatPhoneForDial
import com.example.kodetraineetest.util.extention.toFullString
import com.example.kodetraineetest.util.extention.toLocalDate
import com.example.kodetraineetest.util.makeCall

class UserDetailsFragment() : Fragment() {
    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!
    private var userParcelize:UserParcelize?= null
    private var user:User?= null


    private val router: UserDetailsScreenRouter by lazy {
        UserDetailsScreenRouterImpl(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userParcelize= arguments?.getParcelable(USER)
        userParcelize?.let { user= it.toUser() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)

        backButtonListener()
        phoneClickListener()
        setUserData()
        return binding.root
    }

    private fun setUserData() {
        _binding?.userImgDetails?.load(user?.avatarUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_goose)
            transformations(CircleCropTransformation())
            error(R.drawable.ic_goose)
        }

        _binding?.userNameDetails?.text= "${user?.firstName} ${user?.lastName}"
        _binding?.userTagDetails?.text= user?.userTag
        _binding?.userPositionDetails?.text= user?.position
        _binding?.userBornDetails?.text= user?.birthday?.toLocalDate()?.toFullString()
        _binding?.userYearsDetails?.text= ageDescription(user?.birthday?.toLocalDate(),this.requireContext())
        _binding?.userPhoneDetails?.text= user?.phone?.formatPhone()
    }

    private fun backButtonListener() {
        _binding?.detailBack?.setOnClickListener {
            router.routeToMainScreen()
        }
    }
    private fun phoneClickListener() {
        _binding?.userPhoneDetails?.setOnClickListener {
            user?.phone?.let { makeCall(this.requireContext(), it.formatPhoneForDial()) }
        }
    }

    companion object{
        const val USER= "user_fragment"
    }
}