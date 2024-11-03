package com.uvg.laboratorio8.presentation.mainFlow.location

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.uvg.laboratorio8.presentation.mainFlow.location.locationDetails.locationDetailsScreen
import com.uvg.laboratorio8.presentation.mainFlow.location.locationDetails.navigateToLocationDetailsScreen
import com.uvg.laboratorio8.presentation.mainFlow.location.locationList.LocationListDestination
import com.uvg.laboratorio8.presentation.mainFlow.location.locationList.locationListScreen
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