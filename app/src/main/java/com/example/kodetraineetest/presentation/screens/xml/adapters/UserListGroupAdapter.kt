package com.example.kodetraineetest.presentation.screens.xml.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kodetraineetest.databinding.UserListItemBinding
import com.example.kodetraineetest.databinding.YearHeaderBinding
import com.example.kodetraineetest.presentation.model.RowType
import com.example.kodetraineetest.presentation.model.UserAdapted

class UserListGroupAdapter(
    private val usersGroups: List<UserAdapted>,
    private val userClick: UserClickInterface
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (RowType.values()[viewType]) {
            RowType.Item -> {
                val binding =
                    UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                UserViewHolder(binding, userClick,true)
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
            is UserAdapted.Users -> (holder as UserViewHolder).bind(groups.user)
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

}