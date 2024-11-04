package com.uvg.laboratorio8.presentation.mainFlow.character.characterProfile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.uvg.laboratorio8.data.local.source.CharacterDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterProfileViewModel (
    savedStateHandle: SavedStateHandle

): ViewModel() {
    private val characterDb = CharacterDb()
    private val characterProfile = savedStateHandle.toRoute<CharacterProfileDestination>()
    private val _uiState: MutableStateFlow<CharacterProfileState> = MutableStateFlow(CharacterProfileState())
    val uiState = _uiState.asStateFlow()

    fun getCharacterData() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            delay(2000)
            val character = characterDb.getCharacterById(
                characterProfile.characterId
            )

            _uiState.update { state ->
                state.copy(
                    isLoading = false,
                    data = character
                )
            }
        }
    }

    fun setError() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = false,
                    hasError = true
                )
            }
        }
    }
}