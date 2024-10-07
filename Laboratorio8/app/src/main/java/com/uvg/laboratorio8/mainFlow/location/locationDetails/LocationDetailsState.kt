package com.uvg.laboratorio8.mainFlow.location.locationDetails

import com.uvg.laboratorio8.data.Location

data class LocationDetailsState (
    val isLoading: Boolean = false,
    val data: Location? = null,
    val hasError: Boolean = false
)