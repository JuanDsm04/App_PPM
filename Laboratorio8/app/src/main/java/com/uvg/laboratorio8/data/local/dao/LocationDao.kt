package com.uvg.laboratorio8.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.uvg.laboratorio8.data.local.entity.LocationEntity
import com.uvg.laboratorio8.data.model.Location

@Dao
interface LocationDao {
    @Upsert
    suspend fun insertAll(locations: List<LocationEntity>)

    @Query("SELECT * FROM LocationEntity")
    suspend fun getAllLocations(): List<LocationEntity>

    @Query("SELECT * FROM LocationEntity WHERE id = :id")
    fun getLocation(id: Int): Location
}