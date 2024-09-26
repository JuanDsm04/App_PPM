package com.uvg.laboratorio8.navigation

import com.uvg.laboratorio8.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.uvg.laboratorio8.login.LoginDestination
import com.uvg.laboratorio8.login.loginScreen
import com.uvg.laboratorio8.profile.ProfileDestination
import com.uvg.laboratorio8.profile.profileScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
){

    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    var showNavigationBar by rememberSaveable { mutableStateOf(false)}

    val menuItems = listOf(
        NavigationItem(
            title = "Characters",
            icon = painterResource(id = R.drawable.ic_characters)
        ),
        NavigationItem(
            title = "Locations",
            icon = painterResource(id = R.drawable.ic_locations)
        ),
        NavigationItem(
            title = "Profile",
            icon = painterResource(id = R.drawable.ic_profile)
        )
    )

    Scaffold(
        bottomBar = {
            if (showNavigationBar){
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.secondary
                ) {
                    menuItems.forEachIndexed { index,navigationItem ->

                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick ={ selectedIndex = index
                                when (index) {
                                    0 -> navController.navigateToCharacterGraph()
                                    1 -> navController.navigateToLocationGraph()
                                    2 -> navController.navigate(ProfileDestination){}
                                }
                            },

                            icon = {
                                BadgedBox(badge = {}) {
                                    Icon(
                                        painter = navigationItem.icon,
                                        contentDescription = navigationItem.title
                                    )
                                }
                            },

                            label = { Text(text = navigationItem.title) },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = MaterialTheme.colorScheme.onSecondary,
                                selectedIconColor = MaterialTheme.colorScheme.secondary,
                                selectedTextColor = MaterialTheme.colorScheme.onSecondary
                            )
                        )
                    }
                }
            }
        }

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = LoginDestination,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            loginScreen(
                onLoginClick = {
                    showNavigationBar = true
                    navController.navigateToCharacterGraph(
                        navOptions = NavOptions.Builder().setPopUpTo<LoginDestination>(
                            inclusive = true
                        ).build()
                    )
                }
            )
            characterGraph(navController)
            locationGraph(navController)
            profileScreen(
                onLogOutClick = {
                    selectedIndex = 0
                    showNavigationBar = false
                    navController.navigate(LoginDestination) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}