package com.uvg.laboratorio8.mainFlow.character

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.uvg.laboratorio8.mainFlow.character.charactersList.CharacterListDestination
import com.uvg.laboratorio8.mainFlow.character.charactersList.characterListScreen
import com.uvg.laboratorio8.mainFlow.character.characterProfile.characterProfileScreen
import com.uvg.laboratorio8.mainFlow.character.characterProfile.navigateToCharacterProfileScreen
import kotlinx.serialization.Serializable

@Serializable
data object CharacterNavGraph

fun NavGraphBuilder.characterGraph(
    navController: NavController
) {
    navigation<CharacterNavGraph>(
        startDestination = CharacterListDestination
    ) {
        characterListScreen(
            onCharacterClick = navController::navigateToCharacterProfileScreen
        )
        characterProfileScreen(
            onNavigateBack = navController::navigateUp
        )
    }
}