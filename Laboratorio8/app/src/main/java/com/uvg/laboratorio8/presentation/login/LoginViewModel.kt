package com.uvg.laboratorio8.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.laboratorio8.data.DataStoreUserPrefs
import com.uvg.laboratorio8.dataStore
import com.uvg.laboratorio8.domain.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel (
    private val userPreferences: UserPreferences
): ViewModel() {
    private val _uiState = MutableStateFlow(LoginState())
    val state = _uiState.asStateFlow()

    fun onUsernameChange(username: String) {
        _uiState.update { state ->
            state.copy(
                username = username
            )
        }
    }

    fun saveUsername() {
        viewModelScope.launch {
            userPreferences.setUsername(_uiState.value.username)

            _uiState.update { state ->
                state.copy(
                    loading = true
                )
            }

            //delay(2000)

            _uiState.update { state ->
                state.copy(
                    loading = false
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])

                LoginViewModel(
                    userPreferences = DataStoreUserPrefs(application.dataStore)
                )
            }
        }
    }
}