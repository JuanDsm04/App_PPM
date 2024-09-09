package com.uvg.laboratorio8.characterProfile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class CharacterProfileDestination(
    val characterId : Int
)

fun NavController.navigateToCharacterProfileScreen(
    destination: CharacterProfileDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.characterProfileScreen(
    onNavigateBack: () -> Unit
) {
    composable<CharacterProfileDestination> { navBackStackEntry ->
        val destination: CharacterProfileDestination = navBackStackEntry.toRoute()
        CharacterProfileRoute(
            id = destination.characterId,
            onNavigateBack = onNavigateBack
        )
    }
}