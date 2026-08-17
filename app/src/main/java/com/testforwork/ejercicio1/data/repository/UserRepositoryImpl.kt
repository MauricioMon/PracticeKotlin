package com.testforwork.ejercicio1.data.repository

import com.testforwork.ejercicio1.data.mapper.toDomain
import com.testforwork.ejercicio1.data.remote.RandomUserApi
import com.testforwork.ejercicio1.domain.model.User
import com.testforwork.ejercicio1.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: RandomUserApi
) : UserRepository {

    override suspend fun getUsers(count: Int): Result<List<User>> {
        return try {
            val response = api.getUsers(count)
            val users = response.results.map { it.toDomain() }
            Result.success(users)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}