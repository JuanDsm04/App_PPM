package com.uvg.laboratorio8.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.uvg.laboratorio8.data.local.entity.CharacterEntity
import com.uvg.laboratorio8.data.model.Character

@Dao
interface CharacterDao {
    @Insert
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Query("SELECT * FROM CharacterEntity")
    suspend fun getAllCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM CharacterEntity WHERE id = :id")
    fun getCharacter(id: Int): Character
}