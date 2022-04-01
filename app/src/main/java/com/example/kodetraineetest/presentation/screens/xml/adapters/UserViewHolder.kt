package com.example.kodetraineetest.presentation.screens.xml.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.kodetraineetest.R
import com.example.kodetraineetest.databinding.UserListItemBinding
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.util.extention.toDayMonthString
import com.example.kodetraineetest.util.extention.toLocalDate


class UserViewHolder(
    private val binding: UserListItemBinding,
    private val userClick: UserClickInterface,
    private val showDate:Boolean= false
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private var user: User?= null


    fun bind(user: User) {

        binding.root.setOnClickListener(this)

        this.user= user
        binding.userNameField.text = "${user.firstName} ${user.lastName}"
        binding.userPositionField.text = user.position
        binding.userTagField.text = user.userTag

        if(showDate){
            val date = user.birthday?.toLocalDate()
            date?.let { d ->
                val str = d.toDayMonthString()
                binding.userBornDateField.text = str
                binding.userBornDateField.visibility = View.VISIBLE

            }
        }

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