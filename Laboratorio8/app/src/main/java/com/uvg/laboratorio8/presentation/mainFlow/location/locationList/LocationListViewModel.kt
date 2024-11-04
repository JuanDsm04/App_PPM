package com.uvg.laboratorio8.presentation.mainFlow.location.locationList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.laboratorio8.data.local.repository.LocalLocationRepository
import com.uvg.laboratorio8.data.remote.KtorRickAndMortyAPI
import com.uvg.laboratorio8.di.AppDependencies
import com.uvg.laboratorio8.domain.remote.util.DataError
import com.uvg.laboratorio8.domain.remote.util.onError
import com.uvg.laboratorio8.domain.remote.util.onSuccess
import com.uvg.laboratorio8.domain.repository.LocationRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationListViewModel (
    private val locationRepository: LocationRepository

): ViewModel() {
    private val _uiState: MutableStateFlow<LocationListState> = MutableStateFlow(LocationListState())
    val uiState = _uiState.asStateFlow()

    init {
        getLocationListData()
    }

    fun getLocationListData(){
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            delay(4000)
            locationRepository.getAllLocations()
                .onSuccess { locations ->
                    _uiState.update { state ->
                        state.copy(
                            data = locations,
                            isLoading = false
                        )
                    }
                }
                .onError { error -> setError(error) }
        }
    }

    fun setError(error: DataError) {
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
                val api = KtorRickAndMortyAPI(AppDependencies.provideHttpClient())
                LocationListViewModel(
                    locationRepository = LocalLocationRepository(
                        rickAndMortyAPI = api,
                        locationDao = db.locationDao()
                    )
                )
            }
        }
    }
}