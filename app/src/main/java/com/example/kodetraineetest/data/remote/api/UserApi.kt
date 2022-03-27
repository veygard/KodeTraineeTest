package com.example.kodetraineetest.data.remote.api

import com.example.kodetraineetest.data.remote.model.user.GetUsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface UserApi {
    @Headers(
        "Content-Type: application/json",
        "Prefer: code=200, example=success"
    )

    @GET("users")
    suspend fun getUsers(): Response<GetUsersResponse>
}