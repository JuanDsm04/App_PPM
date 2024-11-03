package com.uvg.laboratorio8.presentation.mainFlow.character.characterProfile

import com.uvg.laboratorio8.data.model.Character

data class CharacterProfileState (
    val isLoading: Boolean = false,
    val data: Character? = null,
    val hasError: Boolean = false
)