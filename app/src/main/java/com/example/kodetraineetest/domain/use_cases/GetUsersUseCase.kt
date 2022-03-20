package com.example.kodetraineetest.domain.use_cases

import com.example.kodetraineetest.domain.repository.UserRepository

class GetUsersUseCase(private val userRepository: UserRepository) {
    suspend fun start() = userRepository.getUsers()
}