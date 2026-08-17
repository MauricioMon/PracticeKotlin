package com.testforwork.ejercicio1.domain.usecase

import com.testforwork.ejercicio1.domain.model.User
import com.testforwork.ejercicio1.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(count: Int = 20): Result<List<User>> {
        return repository.getUsers(count)
    }
}