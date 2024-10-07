package com.uvg.laboratorio8.mainFlow.character.charactersList

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object CharacterListDestination

fun NavGraphBuilder.characterListScreen(
    onCharacterClick: (Int) -> Unit
){
    composable<CharacterListDestination> {
        CharacterListRoute (
            onCharacterClick = onCharacterClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}