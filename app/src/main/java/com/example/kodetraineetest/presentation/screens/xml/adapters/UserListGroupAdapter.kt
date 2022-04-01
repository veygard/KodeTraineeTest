package com.example.kodetraineetest.presentation.screens.xml.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.kodetraineetest.R
import com.example.kodetraineetest.databinding.UserListItemBinding
import com.example.kodetraineetest.databinding.YearHeaderBinding
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.presentation.model.RowType
import com.example.kodetraineetest.presentation.model.UserAdapted
import com.example.kodetraineetest.util.extention.toDayMonthString
import com.example.kodetraineetest.util.extention.toLocalDate

class UserListGroupAdapter(
    private val usersGroups: List<UserAdapted>,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (RowType.values()[viewType]) {
            RowType.Item -> {
                val binding =
                    UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                UserViewHolder(binding)
            }
            RowType.Header -> {
                val binding =
                    YearHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (val groups = usersGroups[position]) {
            is UserAdapted.Users ->  (holder as UserViewHolder).bind(groups.user.user)
            is UserAdapted.Header -> (holder as HeaderViewHolder).bind(groups)
        }

    override fun getItemCount(): Int = usersGroups.size

    override fun getItemViewType(position: Int): Int =
        usersGroups[position].rowType.ordinal

    class HeaderViewHolder(
        private val binding: YearHeaderBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(header: UserAdapted.Header) {
            binding.yearHeaderField.text = header.name
        }
    }

    class UserViewHolder(
        private val binding: UserListItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.userNameField.text = "${user.firstName} ${user.lastName}"
            binding.userPositionField.text = user.position
            binding.userTagField.text = user.userTag

            val date = user.birthday?.toLocalDate()
            date?.let { d ->
                val str = d.toDayMonthString()
                binding.userBornDateField.text = str
                binding.userBornDateField.visibility = View.VISIBLE

            }

            binding.userImg.load(user.avatarUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_goose)
                transformations(CircleCropTransformation())
                error(R.drawable.ic_goose)
            }
        }
    }
}