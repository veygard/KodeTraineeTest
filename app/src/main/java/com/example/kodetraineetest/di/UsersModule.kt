package com.example.kodetraineetest.di

import android.content.Context
import com.example.kodetraineetest.data.remote.api.UserApi
import com.example.kodetraineetest.domain.repository.UserRepository
import com.example.kodetraineetest.domain.repository.UserRepositoryImpl
import com.example.kodetraineetest.domain.use_cases.GetUsersUseCase
import com.example.kodetraineetest.domain.use_cases.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Singleton

@DelicateCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object UsersModule {


    @Provides
    @Singleton
    fun provideUsersRepository(
        usersApiService: UserApi
    ): UserRepository = UserRepositoryImpl(usersApiService)

    @Provides
    @Singleton
    fun provideUseCases(
        userRepository: UserRepository,
    ):UserUseCases = UserUseCases(
        getUsersUseCase = GetUsersUseCase(userRepository = userRepository)
    )



}