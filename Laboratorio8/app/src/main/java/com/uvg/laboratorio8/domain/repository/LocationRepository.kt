package com.uvg.laboratorio8.domain.repository

import com.uvg.laboratorio8.data.model.Location

interface LocationRepository {
    suspend fun initialSync(): Boolean
    suspend fun getLocations(): List<Location>
    suspend fun getLocationById(id: Int): Location
}