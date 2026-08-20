package com.testforwork.ejercicio1.feature.userlist.di

import com.testforwork.ejercicio1.feature.userlist.data.repository.UserRepositoryImpl
import com.testforwork.ejercicio1.feature.userlist.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository
}