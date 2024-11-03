package com.uvg.laboratorio8.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uvg.laboratorio8.navigation.AuthState
import com.uvg.laboratorio8.navigation.AuthViewModel
import com.uvg.laboratorio8.presentation.login.LoginDestination
import com.uvg.laboratorio8.presentation.login.loginScreen
import com.uvg.laboratorio8.presentation.mainFlow.MainNavigationGraph
import com.uvg.laboratorio8.presentation.mainFlow.mainNavigationGraph
import kotlinx.serialization.Serializable

@Serializable
data object SplashDestination

@Composable
fun AppContent(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = viewModel(factory = AuthViewModel.Factory)
) {
    val navController = rememberNavController()
    val authStatus by authViewModel.authStatus.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = SplashDestination,
        modifier = modifier
    ) {
        loginScreen(
            onLoginClick = authViewModel::login
        )

        mainNavigationGraph(onLogOutClick = authViewModel::logout)

        composable<SplashDestination> {}
    }

    LaunchedEffect(authStatus) {
        when (authStatus) {
            AuthState.Authenticated -> {
                navController.navigate(MainNavigationGraph) {
                    popUpTo(LoginDestination) {
                        inclusive = true
                    }
                }
            }

            AuthState.NonAuthenticated -> {
                navController.navigate(LoginDestination) {
                    popUpTo(0)
                }
            }

            AuthState.Loading -> {}
        }
    }
}