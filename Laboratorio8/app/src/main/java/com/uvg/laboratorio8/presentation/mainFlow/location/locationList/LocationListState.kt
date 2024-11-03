package com.uvg.laboratorio8.presentation.mainFlow.location.locationList

import com.uvg.laboratorio8.data.model.Location

data class LocationListState (
    val isLoading: Boolean = false,
    val data: List<Location> = emptyList(),
    val hasError: Boolean = false
)