package com.uvg.laboratorio8.presentation.mainFlow.profile

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

class ProfileViewModel(
    private val userPreferences: UserPreferences
): ViewModel() {
    private val _uiState = MutableStateFlow(ProfileState())
    val state = _uiState.asStateFlow()
    init {
        getName()
    }
    private fun getName() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    username = userPreferences.getValue("username")
                ) }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                ProfileViewModel(
                    userPreferences = DataStoreUserPrefs(application.dataStore)
                )
            }
        }
    }
}