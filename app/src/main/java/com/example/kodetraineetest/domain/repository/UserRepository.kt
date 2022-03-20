package com.example.kodetraineetest.domain.repository


interface UserRepository {
    suspend fun getUsers(): GetUsersResult
}