package com.uvg.laboratorio8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.uvg.laboratorio8.characterProfile.CharacterProfileDestination
import com.uvg.laboratorio8.characterProfile.characterProfileScreen
import com.uvg.laboratorio8.characterProfile.navigateToCharacterProfileScreen
import com.uvg.laboratorio8.charactersList.characterListScreen
import com.uvg.laboratorio8.charactersList.CharacterListDestination
import com.uvg.laboratorio8.charactersList.navigateToCharacterListScreen
import com.uvg.laboratorio8.login.LoginDestination
import com.uvg.laboratorio8.login.loginScreen
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio8Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = LoginDestination,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {

                        loginScreen(
                            onLoginClick = {
                                navController.navigateToCharacterListScreen(
                                    destination = CharacterListDestination,
                                    navOptions = navOptions {
                                        popUpTo<LoginDestination>() { inclusive = true }
                                    }
                                )
                            }
                        )
                        characterListScreen(
                            onCharacterClick = { id ->
                                navController.navigateToCharacterProfileScreen(
                                    destination = CharacterProfileDestination(
                                        characterId = id
                                    ),
                                )
                            }
                        )
                        characterProfileScreen(
                            onNavigateBack = {
                                navController.navigateToCharacterListScreen(
                                    destination = CharacterListDestination,
                                    navOptions = navOptions {
                                        popUpTo(0)
                                    }
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}