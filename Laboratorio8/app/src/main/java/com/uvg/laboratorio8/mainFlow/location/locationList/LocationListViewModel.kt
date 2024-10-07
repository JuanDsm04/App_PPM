package com.uvg.laboratorio8.mainFlow.location.locationList

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.laboratorio8.data.LocationDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationListViewModel (
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val locationDb = LocationDb()
    private val _uiState: MutableStateFlow<LocationListState> = MutableStateFlow(LocationListState())
    val uiState = _uiState.asStateFlow()

    fun getLocationListData(){
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            delay(4000)
            val locations = locationDb.getAllLocations()

            _uiState.update { state ->
                state.copy(
                    isLoading = false,
                    data = locations
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