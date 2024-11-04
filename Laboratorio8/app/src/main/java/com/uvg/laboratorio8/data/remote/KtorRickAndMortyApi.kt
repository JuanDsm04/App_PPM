package com.uvg.laboratorio8.data.remote

import com.uvg.laboratorio8.data.remote.dto.CharactersListDTO
import com.uvg.laboratorio8.data.remote.dto.LocationListDTO
import com.uvg.laboratorio8.data.remote.util.safeCall
import com.uvg.laboratorio8.domain.remote.RickAndMortyAPI
import com.uvg.laboratorio8.domain.remote.util.DataError
import com.uvg.laboratorio8.domain.remote.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class KtorRickAndMortyAPI (
    private val httpClient: HttpClient

): RickAndMortyAPI {
    override suspend fun getAllCharacters(): Result<CharactersListDTO, DataError> {
        return safeCall<CharactersListDTO> {
            httpClient.get("https://rickandmortyapi.com/api/character")
        }
    }
    override suspend fun getAllLocations(): Result<LocationListDTO, DataError> {
        return safeCall<LocationListDTO> {
            httpClient.get("https://rickandmortyapi.com/api/location")
        }
    }
}