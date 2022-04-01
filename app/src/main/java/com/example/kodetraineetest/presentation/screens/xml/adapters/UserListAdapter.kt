package com.example.kodetraineetest.presentation.screens.xml.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.kodetraineetest.R
import com.example.kodetraineetest.databinding.UserListItemBinding
import com.example.kodetraineetest.domain.model.User

class UserListAdapter(private val userList: List<(User)>, private val userClick: UserClickInterface) :
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

    class MyViewHolder(
        private val binding: UserListItemBinding,
        private val userClick: UserClickInterface
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private var user: User?= null


        fun bind(user: User) {

            binding.root.setOnClickListener(this)

            this.user= user
            binding.userNameField.text = "${user.firstName} ${user.lastName}"
            binding.userPositionField.text = user.position
            binding.userTagField.text = user.userTag

            binding.userImg.load(user.avatarUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_goose)
                transformations(CircleCropTransformation())
                error(R.drawable.ic_goose)
            }
        }


        override fun onClick(p0: View?) {
            user?.let { userClick.onUserClick(it) }
        }
    }


}