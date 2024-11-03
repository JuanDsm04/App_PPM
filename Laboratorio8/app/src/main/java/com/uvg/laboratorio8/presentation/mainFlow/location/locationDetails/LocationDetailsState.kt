package com.uvg.laboratorio8.presentation.mainFlow.location.locationDetails

import com.uvg.laboratorio8.data.model.Location

data class LocationDetailsState (
    val isLoading: Boolean = false,
    val data: Location? = null,
    val hasError: Boolean = false
)