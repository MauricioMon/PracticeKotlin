package com.testforwork.ejercicio1.feature.userlist.domain.repository

import com.testforwork.ejercicio1.feature.userlist.domain.model.User

interface UserRepository {
    suspend fun getUsers(count: Int): Result<List<User>>
}