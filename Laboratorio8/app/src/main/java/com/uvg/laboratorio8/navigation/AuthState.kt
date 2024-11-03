package com.uvg.laboratorio8.navigation

sealed interface AuthState {
    data object Loading: AuthState
    data object Authenticated: AuthState
    data object NonAuthenticated: AuthState
}