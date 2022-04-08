package com.example.kodetraineetest.presentation.screens.xml.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kodetraineetest.databinding.UserListItemBinding
import com.example.kodetraineetest.domain.model.User

class UserListAdapter(private val userList: List<(User)>, private val userClick: UserClickInterface?) :
    RecyclerView.Adapter<UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UserListItemBinding.inflate(layoutInflater, parent, false)
        return UserViewHolder(binding, userClick)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): User = userList[position]

    override fun getItemCount(): Int = userList.size

}