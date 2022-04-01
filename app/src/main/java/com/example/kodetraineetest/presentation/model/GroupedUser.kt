package com.example.kodetraineetest.presentation.model

import com.example.kodetraineetest.domain.model.User

sealed class UserAdapted(
    val rowType: RowType
){
    data class Users(val user: UserYearGrouped) :
        UserAdapted(RowType.Item)

    data class Header(val name: String) : UserAdapted(RowType.Header)
}

enum class YearsType{
    This, Next
}
enum class RowType {
    Item,
    Header
}

data class UserYearGrouped(
    val type: YearsType,
    val user: User
)