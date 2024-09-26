package com.uvg.laboratorio8.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.uvg.laboratorio8.location.locationDetails.locationDetailsScreen
import com.uvg.laboratorio8.location.locationDetails.navigateToLocationDetailsScreen
import com.uvg.laboratorio8.location.locationList.LocationListDestination
import com.uvg.laboratorio8.location.locationList.locationListScreen
import kotlinx.serialization.Serializable

@Serializable
data object  LocationNavigationGraph

fun NavController.navigateToLocationGraph(navOptions: NavOptions? = null) {
    this.navigate(LocationNavigationGraph, navOptions)
}

fun NavGraphBuilder.locationGraph(
    navController: NavController
) {
    navigation<LocationNavigationGraph>(
        startDestination = LocationListDestination
    ) {

        locationListScreen(
            onLocationClick = navController::navigateToLocationDetailsScreen
        )
        locationDetailsScreen(
            onNavigateBack = navController::navigateUp
        )
    }

}