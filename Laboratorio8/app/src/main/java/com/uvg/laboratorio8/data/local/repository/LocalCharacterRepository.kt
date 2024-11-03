package com.uvg.laboratorio8.data.local.repository

import com.uvg.laboratorio8.data.local.entity.mapToEntity
import com.uvg.laboratorio8.data.local.entity.mapToModel
import com.uvg.laboratorio8.data.local.dao.CharacterDao
import com.uvg.laboratorio8.data.model.Character
import com.uvg.laboratorio8.data.local.source.CharacterDb

class LocalCharacterRepository(private val characterDao: CharacterDao) {
    suspend fun getAllCharacters(): List<Character> {
        val localCharacters = characterDao.getAllCharacters()
        return localCharacters.map { characterEntity ->
            characterEntity.mapToModel()
        }
    }

    fun getCharacterByID(id: Int): Character {
        return characterDao.getCharacter(id)
    }

    suspend fun insertAllCharacters() {
        val characters = characterDao.getAllCharacters()

        if (characters.isEmpty()) {
            val remoteCharacters = CharacterDb.getAllCharacters()
            val localCharacters = remoteCharacters.map { character ->
                character.mapToEntity()
            }
            characterDao.insertAll(localCharacters)
        }
    }
}