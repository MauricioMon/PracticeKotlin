package com.testforwork.ejercicio1.domain.repository

import com.testforwork.ejercicio1.domain.model.User

interface UserRepository {
    suspend fun getUsers(count: Int): Result<List<User>>
}