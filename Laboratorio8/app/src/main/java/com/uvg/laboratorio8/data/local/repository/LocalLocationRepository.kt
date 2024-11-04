package com.uvg.laboratorio8.data.local.repository

import com.uvg.laboratorio8.data.local.dao.LocationDao
import com.uvg.laboratorio8.data.model.Location
import com.uvg.laboratorio8.data.remote.dto.mapToEntity
import com.uvg.laboratorio8.domain.remote.RickAndMortyAPI
import com.uvg.laboratorio8.domain.remote.util.DataError
import com.uvg.laboratorio8.domain.remote.util.Result
import com.uvg.laboratorio8.domain.repository.LocationRepository
import com.uvg.laboratorio8.data.remote.dto.mapToModel
import com.uvg.laboratorio8.data.local.entity.mapToModel

class LocalLocationRepository(
    private val locationDao: LocationDao,
    private val rickAndMortyAPI: RickAndMortyAPI

): LocationRepository {
    override suspend fun getAllLocations(): Result<List<Location>, DataError> {

        when (val result = rickAndMortyAPI.getAllLocations()) {
            is Result.Error -> {
                val localLocations = locationDao.getAllLocations()

                return if (localLocations.isEmpty()) {
                    if(result.error == DataError.NO_INTERNET) {
                        Result.Error(
                            DataError.NO_INTERNET
                        )
                    } else {
                        Result.Error(DataError.GENERIC_FAILURE)
                    }

                } else {
                    Result.Success (
                        localLocations.map { it.mapToModel() }
                    )
                }
            }

            is Result.Success -> {
                val remoteLocations = result.data.results

                locationDao.insertAll(
                    remoteLocations.map { it.mapToEntity() }
                )
                return Result.Success(
                    remoteLocations.map { it.mapToModel() }
                )
            }
        }
    }

    override suspend fun getLocationByID(id: Int): Location {
        return locationDao.getLocation(id)
    }
}