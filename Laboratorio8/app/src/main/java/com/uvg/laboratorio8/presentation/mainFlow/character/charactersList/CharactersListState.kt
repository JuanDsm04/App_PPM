package com.uvg.laboratorio8.presentation.mainFlow.character.charactersList

import com.uvg.laboratorio8.data.model.Character

data class CharactersListState (
    val isLoading: Boolean = false,
    val data: List<Character> = emptyList(),
    val hasError: Boolean = false
)