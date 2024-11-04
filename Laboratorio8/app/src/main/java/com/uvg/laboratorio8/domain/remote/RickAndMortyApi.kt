package com.uvg.laboratorio8.domain.remote

import com.uvg.laboratorio8.data.remote.dto.CharactersListDTO
import com.uvg.laboratorio8.data.remote.dto.LocationListDTO
import com.uvg.laboratorio8.domain.remote.util.DataError
import com.uvg.laboratorio8.domain.remote.util.Result

interface RickAndMortyAPI {
    suspend fun getAllCharacters(): Result<CharactersListDTO, DataError>
    suspend fun getAllLocations(): Result<LocationListDTO, DataError>
}