package com.uvg.laboratorio8.mainFlow.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.uvg.laboratorio8.data.DataStoreUserPreferences
import com.uvg.laboratorio8.dataStore
import com.uvg.laboratorio8.domain.UserPreferences

class ProfileViewModel(
    private val userPreferences: UserPreferences

): ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    init {
        getName()
    }

    private fun getName() {
        viewModelScope.launch {
            _state.update { it.copy(
                name = userPreferences.getValue("name")
            )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {

            initializer {
                val application = checkNotNull( this[ APPLICATION_KEY ] )

                ProfileViewModel (
                    userPreferences = DataStoreUserPreferences( application.dataStore )
                )
            }
        }
    }
}