package com.uvg.laboratorio8.mainFlow

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
data object MainNavigationGraph

fun NavGraphBuilder.mainNavigationGraph(
    onLogOutClick: () -> Unit
) {
    composable<MainNavigationGraph> {
        MainFlowScreen(
            navController = rememberNavController(),
            onLogOutClick = onLogOutClick
        )
    }
}

fun NavController.navigateToMainGraph(navOptions: NavOptions? = null) {
    this.navigate(MainNavigationGraph, navOptions)
}
