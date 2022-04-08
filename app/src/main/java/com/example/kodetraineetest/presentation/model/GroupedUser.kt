package com.example.kodetraineetest.presentation.model

import com.example.kodetraineetest.domain.model.User

sealed class UserAdapted(
    val rowType: RowType
){
    data class Users(val user: User) :
        UserAdapted(RowType.Item)

    data class Header(val name: String) : UserAdapted(RowType.Header)
}

enum class RowType {
    Item,
    Header
}

