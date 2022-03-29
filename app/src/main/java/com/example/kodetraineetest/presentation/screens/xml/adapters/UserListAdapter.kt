package com.example.kodetraineetest.presentation.screens.xml.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.kodetraineetest.R
import com.example.kodetraineetest.databinding.UserListItemBinding
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.util.extention.toDayMonthString
import com.example.kodetraineetest.util.extention.toLocalDate

class UserListAdapter (private val userList: List<(User)>) :
    RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UserListItemBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    private fun getItem(position: Int): User = userList[position]

    override fun getItemCount(): Int = userList.size

    class MyViewHolder(private val binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User){
            binding.userNameField.text = "${user.firstName} ${user.lastName}"
            binding.userPositionField.text = user.position
            binding.userTagField.text = user.userTag

            val date = user.birthday?.toLocalDate()
            date?.let { d ->
                val str = d.toDayMonthString()
                binding.userBornDateField.text = str
            }
            binding.userPositionField.tag = user.userTag

            binding.userImg.load(user.avatarUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_goose)
                transformations(CircleCropTransformation())
                error(R.drawable.ic_goose)
            }
        }
    }
}