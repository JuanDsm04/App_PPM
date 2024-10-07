package com.uvg.laboratorio8.mainFlow.location.locationDetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class LocationDetailsDestination(
    val locationId: Int
)

fun NavController.navigateToLocationDetailsScreen(
    locationId: Int,
    navOptions: NavOptions? = null
) {
    this.navigate(
        route = LocationDetailsDestination(locationId = locationId),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.locationDetailsScreen(
    onNavigateBack: () -> Unit
) {
    composable<LocationDetailsDestination> { navBackStackEntry ->
        val destination: LocationDetailsDestination = navBackStackEntry.toRoute()
        LocationDetailsRoute(
            onNavigateBack = onNavigateBack
        )
    }
}