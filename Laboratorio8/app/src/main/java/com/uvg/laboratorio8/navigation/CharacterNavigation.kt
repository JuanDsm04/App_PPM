package com.uvg.laboratorio8.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.uvg.laboratorio8.character.characterProfile.characterProfileScreen
import com.uvg.laboratorio8.character.characterProfile.navigateToCharacterProfileScreen
import com.uvg.laboratorio8.character.charactersList.CharacterListDestination
import com.uvg.laboratorio8.character.charactersList.characterListScreen
import kotlinx.serialization.Serializable

@Serializable
data object  CharacterNavigationGraph

fun NavController.navigateToCharacterGraph(navOptions: NavOptions? = null) {
    this.navigate(CharacterNavigationGraph, navOptions)
}

fun NavGraphBuilder.characterGraph(
    navController: NavController
) {
    navigation<CharacterNavigationGraph>(
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