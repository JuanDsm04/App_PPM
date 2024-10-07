package com.uvg.laboratorio8.mainFlow.location.locationList

import com.uvg.laboratorio8.data.Location

data class LocationListState (
    val isLoading: Boolean = false,
    val data: List<Location> = emptyList(),
    val hasError: Boolean = false
)