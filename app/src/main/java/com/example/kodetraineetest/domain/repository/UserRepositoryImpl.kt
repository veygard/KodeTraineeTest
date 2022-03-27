package com.example.kodetraineetest.domain.repository

import com.example.kodetraineetest.data.remote.api.UserApi
import com.example.kodetraineetest.data.remote.model.user.toDomainList
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val usersApiService: UserApi
) : UserRepository {

    override suspend fun getUsers(): GetUsersResult {

        var result: GetUsersResult = GetUsersResult.EnqueueError("usersApiService.getUsers() not working")

        try {
            val call =  usersApiService.getUsers()
            when{
                call.isSuccessful->{
                    call.body()?.userList?.toDomainList()?.let {
                        result = GetUsersResult.UserList(it)
                    } ?: run {
                        result = GetUsersResult.ServerError(
                            error = call.errorBody()?.source()?.buffer?.snapshot()?.utf8()
                        )
                    }
                }
                call.code() in 400..499 ->{
                    result = GetUsersResult.ServerError(
                        error = "Client Error: ${call.errorBody()?.source()?.buffer?.snapshot()?.utf8()}"
                    )
                }
                call.code() in 500..599 ->{
                    result = GetUsersResult.ServerError(
                        error = "Server Error: ${call.errorBody()?.source()?.buffer?.snapshot()?.utf8()}"
                    )

                }
                else -> {
                    result = GetUsersResult.ServerError(
                        error = "Unknown Error: ${call.errorBody()?.source()?.buffer?.snapshot()?.utf8()}"
                    )

                }
            }
        } catch (e: Exception) {
                result = GetUsersResult.ConnectionError()
        }
        return result
    }
}