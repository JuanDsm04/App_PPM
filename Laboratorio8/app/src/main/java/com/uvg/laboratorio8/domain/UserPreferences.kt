package com.uvg.laboratorio8.domain

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    fun authStatus(): Flow<Boolean>
    suspend fun setUsername(username: String)
    suspend fun getValue(key: String): String?
    suspend fun logIn()
    suspend fun logOut()
}