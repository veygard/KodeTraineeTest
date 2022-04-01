package com.example.kodetraineetest.presentation.screens.xml.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kodetraineetest.R
import com.example.kodetraineetest.databinding.FragmentUserListBinding
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.presentation.screens.xml.adapters.UserClickInterface
import com.example.kodetraineetest.presentation.screens.xml.adapters.UserListAdapter

class UserListFragment(private val userList: List<User>, private val userClick: UserClickInterface): Fragment(R.layout.fragment_user_list) {
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

        val adapter = UserListAdapter(userList = userList, userClick = userClick)
        binding.recyclerUserList.adapter= adapter
        binding.recyclerUserList.layoutManager= LinearLayoutManager(this.requireContext())
        val decoration = DividerItemDecoration(this.requireContext(), DividerItemDecoration.HORIZONTAL)
        binding.recyclerUserList.addItemDecoration(decoration)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}