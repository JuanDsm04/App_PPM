package com.uvg.laboratorio8.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object ProfileDestination

fun NavGraphBuilder.profileScreen(
    onLogOutClick: () -> Unit
) {

    composable<ProfileDestination> { 
        ProfileRoute(
            onLogOutClick = onLogOutClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}