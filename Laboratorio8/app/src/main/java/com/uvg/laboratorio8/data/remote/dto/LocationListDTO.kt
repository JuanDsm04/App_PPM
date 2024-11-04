package com.uvg.laboratorio8.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationListDTO(
    val results: List<LocationDTO>
)