package com.uvg.laboratorio8.presentation.mainFlow.location.locationList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.laboratorio8.data.local.dao.LocationDao
import com.uvg.laboratorio8.data.local.repository.LocalLocationRepository
import com.uvg.laboratorio8.di.AppDependencies
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationListViewModel (
    private val locationDao: LocationDao
): ViewModel() {
    private val localLocationRepository = LocalLocationRepository(locationDao)
    private val _uiState: MutableStateFlow<LocationListState> = MutableStateFlow(LocationListState())
    val uiState = _uiState.asStateFlow()

    fun getLocationListData(){
        viewModelScope.launch {
            localLocationRepository.insertAllLocations()

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
                    data = localLocationRepository.getAllLocations()
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
                LocationListViewModel(
                    locationDao = db.locationDao()
                )
            }
        }
    }
}