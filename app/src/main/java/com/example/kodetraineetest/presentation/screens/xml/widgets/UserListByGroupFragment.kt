package com.example.kodetraineetest.presentation.screens.xml.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kodetraineetest.R
import com.example.kodetraineetest.databinding.FragmentUserListBinding
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.presentation.model.UserAdapted
import com.example.kodetraineetest.presentation.model.UserYearGrouped
import com.example.kodetraineetest.presentation.model.YearsType
import com.example.kodetraineetest.presentation.screens.xml.adapters.UserClickInterface
import com.example.kodetraineetest.presentation.screens.xml.adapters.UserListGroupAdapter
import com.example.kodetraineetest.util.Constants
import com.example.kodetraineetest.util.extention.toLocalDate
import java.time.LocalDate

class UserListByGroupFragment(private val userList: List<User>, private val userClick: UserClickInterface): androidx.fragment.app.Fragment(R.layout.fragment_user_list) {
    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = UserListGroupAdapter(setupYearGroups(userList), userClick)
        binding.recyclerUserList.adapter= adapter
        binding.recyclerUserList.layoutManager= LinearLayoutManager(this.requireContext())
        val decoration = DividerItemDecoration(this.requireContext(), DividerItemDecoration.HORIZONTAL)
        binding.recyclerUserList.addItemDecoration(decoration)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupYearGroups(
        list: List<User>
    ): List<UserAdapted> {
        /*разбиваем юзеров по годам*/
        var usersThisYear= mutableListOf<UserYearGrouped>(
        )
        var usersNextYear= mutableListOf<UserYearGrouped>(
        )
        list.forEach { user ->
            val date = user.birthday?.toLocalDate()
            date?.let { d ->
                val day = d.dayOfMonth
                val month = d.month
                val year = LocalDate.now().year
                val newDate = LocalDate.of(year, month, day)

                if (newDate.isAfter(LocalDate.now())) {
                    usersThisYear.add(UserYearGrouped(type = YearsType.This, user))
                } else {
                    usersNextYear.add(UserYearGrouped(type = YearsType.Next, user))
                }
            }
        }

        /*сортируем списки*/
        usersThisYear= usersThisYear.sortedWith(
            compareBy(
                { it.user.birthday?.toLocalDate()?.month },
                { it.user.birthday?.toLocalDate()?.dayOfMonth },
            )
        ).toMutableList()

        usersNextYear= usersNextYear.sortedWith(
            compareBy(
                { it.user.birthday?.toLocalDate()?.month },
                { it.user.birthday?.toLocalDate()?.dayOfMonth },
            )
        ).toMutableList()



        /*делаем список для адаптера: юзеры этого года, заголовок, юзеры следующего*/
        val userAdapted= mutableListOf<UserAdapted>()
        usersThisYear.forEach { userYearGrouped ->
            userAdapted.add(UserAdapted.Users(userYearGrouped))
        }
        userAdapted.add(UserAdapted.Header(Constants.NEXT_YEAR))
        usersNextYear.forEach { userYearGrouped ->
            userAdapted.add(UserAdapted.Users(userYearGrouped))
        }

        return userAdapted
    }

}