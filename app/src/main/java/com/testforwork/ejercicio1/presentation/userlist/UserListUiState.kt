package com.testforwork.ejercicio1.presentation.userlist

import com.testforwork.ejercicio1.domain.model.User

sealed interface UserListUiState {
    object Loading : UserListUiState
    data class Success(val users: List<User>) : UserListUiState
    data class Error(val message: String) : UserListUiState
}