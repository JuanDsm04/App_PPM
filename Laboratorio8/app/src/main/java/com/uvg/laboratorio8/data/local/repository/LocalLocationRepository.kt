package com.uvg.laboratorio8.data.local.repository

import com.uvg.laboratorio8.data.local.dao.LocationDao
import com.uvg.laboratorio8.data.local.entity.mapToEntity
import com.uvg.laboratorio8.data.local.entity.mapToModel
import com.uvg.laboratorio8.data.model.Location
import com.uvg.laboratorio8.data.local.source.LocationDb
import com.uvg.laboratorio8.domain.repository.LocationRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

class LocalLocationRepository(
    private val locationDao: LocationDao

) {
    suspend fun getAllLocations(): List<Location> {
        val localLocations = locationDao.getAllLocations()
        return localLocations.map { location ->
            location.mapToModel()
        }
    }

    fun getLocationByID(id: Int): Location {
        return locationDao.getLocation(id)
    }

    suspend fun insertAllLocations() {
        val locations = locationDao.getAllLocations()
        if (locations.isEmpty()) {
            val remoteLocations = LocationDb.getAllLocations()
            val localLocations = remoteLocations.map { localLocation ->
                localLocation.mapToEntity()
            }
            locationDao.insertAll(localLocations)
        }
    }
}