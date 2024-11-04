package com.uvg.laboratorio8.presentation.mainFlow.character.charactersList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.laboratorio8.data.local.repository.LocalCharacterRepository
import com.uvg.laboratorio8.data.remote.KtorRickAndMortyAPI
import com.uvg.laboratorio8.di.AppDependencies
import com.uvg.laboratorio8.domain.remote.util.DataError
import com.uvg.laboratorio8.domain.remote.util.onError
import com.uvg.laboratorio8.domain.remote.util.onSuccess
import com.uvg.laboratorio8.domain.repository.CharacterRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharactersListViewModel(
    private val characterRepository: CharacterRepository

) : ViewModel() {
    private val _uiState: MutableStateFlow<CharactersListState> = MutableStateFlow(CharactersListState())
    val uiState = _uiState.asStateFlow()

    init {
        getCharacterListData()
    }

    fun getCharacterListData() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            delay(4000)
            characterRepository.getAllCharacters()
                .onSuccess { characters ->
                    _uiState.update { state ->
                        state.copy(
                            data = characters,
                            isLoading = false,
                            hasError = false
                        )
                    }
                }
                .onError { error -> setError(error) }
        }
    }

    fun setError(error: DataError){
        _uiState.update { state ->
            state.copy(
                hasError = true,
                isLoading = false
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val db = AppDependencies.provideDatabase(application)
                val api = KtorRickAndMortyAPI(AppDependencies.provideHttpClient())
                CharactersListViewModel(
                    characterRepository = LocalCharacterRepository(
                        rickAndMortyAPI = api,
                        characterDao = db.characterDao()
                    )
                )
            }
        }
    }
}