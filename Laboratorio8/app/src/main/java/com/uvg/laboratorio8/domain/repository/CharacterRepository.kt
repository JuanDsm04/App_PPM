package com.uvg.laboratorio8.domain.repository

import com.uvg.laboratorio8.data.model.Character
import com.uvg.laboratorio8.domain.remote.util.DataError
import com.uvg.laboratorio8.domain.remote.util.Result

interface CharacterRepository {
    suspend fun getAllCharacters(): Result<List<Character>, DataError>
    suspend fun getCharacterByID(id: Int): Character
}