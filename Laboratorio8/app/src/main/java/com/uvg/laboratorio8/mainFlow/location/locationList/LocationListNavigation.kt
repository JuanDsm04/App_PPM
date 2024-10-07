package com.uvg.laboratorio8.mainFlow.location.locationList

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object LocationListDestination

fun NavGraphBuilder.locationListScreen(
    onLocationClick: (Int) -> Unit
){
    composable<LocationListDestination>{
        LocationListRoute(
            onLocationClick = onLocationClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}