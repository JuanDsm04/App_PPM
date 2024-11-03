package com.uvg.laboratorio8.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.uvg.laboratorio8.presentation.mainFlow.character.CharacterNavGraph
import com.uvg.laboratorio8.presentation.mainFlow.character.charactersList.CharacterListDestination
import com.uvg.laboratorio8.presentation.mainFlow.location.LocationsNavGraph
import com.uvg.laboratorio8.presentation.mainFlow.location.locationList.LocationListDestination
import com.uvg.laboratorio8.presentation.mainFlow.profile.ProfileDestination

data class NavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val destination: Any,
)

val navigationItems = listOf(
    NavItem(
        title = "Characters",
        selectedIcon = Icons.Filled.Face,
        unselectedIcon = Icons.Outlined.Face,
        destination = CharacterNavGraph
    ),
    NavItem(
        title = "Locations",
        selectedIcon = Icons.Filled.LocationOn,
        unselectedIcon = Icons.Outlined.LocationOn,
        destination = LocationsNavGraph
    ),
    NavItem(
        title = "Profile",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        destination = ProfileDestination
    )
)

val topLevelDestinations = listOf(
    CharacterListDestination::class,
    LocationListDestination::class,
    ProfileDestination::class
)

