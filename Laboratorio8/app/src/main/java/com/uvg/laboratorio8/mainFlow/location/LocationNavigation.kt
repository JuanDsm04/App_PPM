package com.uvg.laboratorio8.mainFlow.location

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.uvg.laboratorio8.mainFlow.location.locationList.LocationListDestination
import com.uvg.laboratorio8.mainFlow.location.locationList.locationListScreen
import com.uvg.laboratorio8.mainFlow.location.locationDetails.locationDetailsScreen
import com.uvg.laboratorio8.mainFlow.location.locationDetails.navigateToLocationDetailsScreen
import kotlinx.serialization.Serializable

@Serializable
data object LocationsNavGraph

fun NavController.navigateToLocationsGraph(navOptions: NavOptions? = null) {
    this.navigate(LocationsNavGraph, navOptions)
}

fun NavGraphBuilder.locationsGraph(
    navController: NavController
) {
    navigation<LocationsNavGraph>(
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