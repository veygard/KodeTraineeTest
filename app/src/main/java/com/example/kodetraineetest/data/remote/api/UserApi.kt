package com.example.kodetraineetest.data.remote.api

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.kodetraineetest.data.remote.model.user.GetUsersResponse
import com.example.kodetraineetest.data.remote.model.user.UserDTO
import com.example.kodetraineetest.data.remote.model.user.toDomainList
import com.example.kodetraineetest.domain.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
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