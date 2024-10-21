package com.uvg.laboratorio8.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.uvg.laboratorio8.data.DataStoreUserPreferences
import com.uvg.laboratorio8.dataStore
import com.uvg.laboratorio8.domain.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow

class LoginViewModel (
    private val userPreferences: UserPreferences

): ViewModel() {
    private val _state = MutableStateFlow( LoginState() )
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {

            is LoginEvent.NameChange -> {
                _state.update { it.copy( name = event.name )}
            }
            LoginEvent.SaveName -> saveName()
        }
    }

    private fun saveName() {
        viewModelScope.launch {
            userPreferences.setName(_state.value.name)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull( this[ APPLICATION_KEY ] )

                LoginViewModel(
                    userPreferences = DataStoreUserPreferences( application.dataStore )
                )
            }
        }
    }
}