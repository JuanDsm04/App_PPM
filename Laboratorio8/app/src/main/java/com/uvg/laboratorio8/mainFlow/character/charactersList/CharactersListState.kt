package com.uvg.laboratorio8.mainFlow.character.charactersList

import com.uvg.laboratorio8.data.Character

data class CharactersListState (
    val isLoading: Boolean = false,
    val data: List<Character> = emptyList(),
    val hasError: Boolean = false
)