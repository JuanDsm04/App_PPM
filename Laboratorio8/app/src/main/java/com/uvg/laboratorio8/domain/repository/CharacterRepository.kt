package com.uvg.laboratorio8.domain.repository

import com.uvg.laboratorio8.data.model.Character

interface CharacterRepository {
    suspend fun initialSync(): Boolean
    suspend fun getCharacters(): List<Character>
    suspend fun getCharacterById(id: Int): Character
}