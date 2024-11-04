package com.uvg.laboratorio8.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharactersListDTO(
    val results: List<CharacterDTO>
)