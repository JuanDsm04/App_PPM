package com.uvg.laboratorio8.data.local.repository

import com.uvg.laboratorio8.data.local.dao.CharacterDao
import com.uvg.laboratorio8.domain.remote.RickAndMortyAPI
import com.uvg.laboratorio8.domain.remote.util.DataError
import com.uvg.laboratorio8.domain.remote.util.Result
import com.uvg.laboratorio8.domain.repository.CharacterRepository
import com.uvg.laboratorio8.data.model.Character
import com.uvg.laboratorio8.data.remote.dto.mapToEntity
import com.uvg.laboratorio8.data.remote.dto.mapToModel
import com.uvg.laboratorio8.data.local.entity.mapToModel

class LocalCharacterRepository(
    private val characterDao: CharacterDao,
    private val rickAndMortyAPI: RickAndMortyAPI

): CharacterRepository {
    override suspend fun getAllCharacters(): Result<List<Character>, DataError> {
        when (val result = rickAndMortyAPI.getAllCharacters()) {
            is Result.Error -> {
                val localCharacters = characterDao.getAllCharacters()

                return if (localCharacters.isEmpty()) {
                    if(result.error == DataError.NO_INTERNET) {
                        Result.Error(
                            DataError.NO_INTERNET
                        )
                    } else {
                        Result.Error(DataError.GENERIC_FAILURE)
                    }

                } else {
                    Result.Success(
                        localCharacters.map { it.mapToModel() }
                    )
                }
            }

            is Result.Success -> {
                val remoteCharacter = result.data.results

                characterDao.insertAll(
                    remoteCharacter.map { it.mapToEntity() }
                )
                return Result.Success(
                    remoteCharacter.map { it.mapToModel() }
                )
            }
        }
    }

    override suspend fun getCharacterByID(id: Int): Character {
        return characterDao.getCharacter(id)
    }
}