package com.testforwork.ejercicio1.feature.userlist.presentation

import com.testforwork.ejercicio1.feature.userlist.domain.model.User

sealed interface UserListUiState {
    object Loading : UserListUiState
    data class Success(val users: List<User>) : UserListUiState
    data class Error(val message: String) : UserListUiState
}