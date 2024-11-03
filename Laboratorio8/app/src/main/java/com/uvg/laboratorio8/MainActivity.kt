package com.uvg.laboratorio8

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
import com.uvg.laboratorio8.navigation.AppNavigation
import com.uvg.laboratorio8.navigation.AuthState
import com.uvg.laboratorio8.navigation.AuthViewModel
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels { AuthViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        installSplashScreen().setKeepOnScreenCondition {
//            authViewModel.authStatus.value is AuthState.Loading
//        }

        setContent {
            val navController = rememberNavController()
            Laboratorio8Theme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    AppNavigation(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        navController = navController
                    )
                }
            }
        }
    }
}