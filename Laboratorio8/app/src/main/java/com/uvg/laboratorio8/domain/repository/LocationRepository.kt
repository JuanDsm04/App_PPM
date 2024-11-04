package com.uvg.laboratorio8.domain.repository

import com.uvg.laboratorio8.data.model.Location
import com.uvg.laboratorio8.domain.remote.util.DataError
import com.uvg.laboratorio8.domain.remote.util.Result

interface LocationRepository {
    suspend fun getAllLocations(): Result<List<Location>, DataError>
    suspend fun getLocationByID(id: Int): Location
}