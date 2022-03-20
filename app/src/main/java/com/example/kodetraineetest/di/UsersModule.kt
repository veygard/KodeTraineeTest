package com.example.kodetraineetest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Singleton

@DelicateCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object UsersModule {

    @Provides
    @Singleton
    fun provideUsersApi(
    ) {

    }

    @Provides
    @Singleton
    fun provideUsersRepository(
    ) {

    }

    @Provides
    @Singleton
    fun provideUseCases(usesCases: String) {
    }

}