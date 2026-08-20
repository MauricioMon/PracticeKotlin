package com.testforwork.ejercicio1.feature.userlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testforwork.ejercicio1.feature.userlist.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface UserListEvent {
    data object LoadUsers : UserListEvent
    data object Refresh : UserListEvent
}

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserListUiState>(UserListUiState.Loading)
    val uiState: StateFlow<UserListUiState> = _uiState.asStateFlow()

    init {
        onEvent(UserListEvent.LoadUsers)
    }

    fun onEvent(event: UserListEvent) {
        when (event) {
            is UserListEvent.LoadUsers, is UserListEvent.Refresh -> loadUsers()
        }
    }

    private fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = UserListUiState.Loading
            val result = getUsersUseCase(count = 20)
            _uiState.value = result.fold(
                onSuccess = { users -> UserListUiState.Success(users) },
                onFailure = { error -> UserListUiState.Error(error.message ?: "Error desconocido") }
            )
        }
    }
}