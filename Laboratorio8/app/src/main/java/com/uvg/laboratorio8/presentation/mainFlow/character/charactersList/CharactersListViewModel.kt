package com.uvg.laboratorio8.presentation.mainFlow.character.charactersList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.laboratorio8.data.local.dao.CharacterDao
import com.uvg.laboratorio8.data.local.repository.LocalCharacterRepository
import com.uvg.laboratorio8.di.AppDependencies
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharactersListViewModel(
    private val characterDao: CharacterDao

): ViewModel() {
    private val localCharacterRepository = LocalCharacterRepository(characterDao)
    private val _uiState: MutableStateFlow<CharactersListState> = MutableStateFlow(CharactersListState())
    val uiState = _uiState.asStateFlow()

    fun getCharacterListData() {
        viewModelScope.launch {
            localCharacterRepository.insertAllCharacters()

            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            delay(4000)

            _uiState.update { state ->
                state.copy(
                    isLoading = false,
                    data = localCharacterRepository.getAllCharacters()
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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val db = AppDependencies.provideDatabase(application)

                CharactersListViewModel(
                    characterDao = db.characterDao()
                )
            }
        }
    }
}