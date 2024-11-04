package com.uvg.laboratorio8.data.remote.dto

import com.uvg.laboratorio8.data.local.entity.LocationEntity
import com.uvg.laboratorio8.data.model.Location
import kotlinx.serialization.Serializable

@Serializable
data class LocationDTO(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String
)

fun LocationDTO.mapToModel(): Location {
    return Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}

fun LocationDTO.mapToEntity(): LocationEntity {
    return LocationEntity (
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}