package com.uvg.laboratorio8.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.uvg.laboratorio8.data.local.entity.CharacterEntity
import com.uvg.laboratorio8.data.model.Character

@Dao
interface CharacterDao {
    @Upsert
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Query("SELECT * FROM CharacterEntity")
    suspend fun getAllCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM CharacterEntity WHERE id = :id")
    fun getCharacter(id: Int): Character
}