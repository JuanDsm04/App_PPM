package com.uvg.laboratorio8.mainFlow.character.charactersList

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.laboratorio8.data.CharacterDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharactersListViewModel(
    savedStatedHandle: SavedStateHandle
): ViewModel() {
    private val characterDb = CharacterDb()
    private val _uiState: MutableStateFlow<CharactersListState> = MutableStateFlow(CharactersListState())
    val uiState = _uiState.asStateFlow()

    fun getCharacterListData() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            delay(4000)
            val characters = characterDb.getAllCharacters()

            _uiState.update { state ->
                state.copy(
                    isLoading = false,
                    data = characters
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