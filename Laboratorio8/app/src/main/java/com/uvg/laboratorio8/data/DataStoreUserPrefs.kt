package com.uvg.laboratorio8.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.uvg.laboratorio8.domain.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreUserPrefs (
    private val dataStore: DataStore<Preferences>

): UserPreferences {
    private val usernameKey = stringPreferencesKey("username")
    private val isAuthenticatedKey = booleanPreferencesKey("isAuthenticated")

    override suspend fun logIn() {
        dataStore.edit { preferences ->
            preferences[ isAuthenticatedKey ] =true
        }
    }

    override suspend fun logOut() {
        dataStore.edit { preferences ->
            preferences[ isAuthenticatedKey ] = false
        }
    }

    override suspend fun setUsername( username: String ) {
        dataStore.edit { preferences ->
            preferences[usernameKey]= username
        }
    }

    override suspend fun getValue(key: String): String? {
        val preferencesKey = when (key) {
            "username" -> usernameKey
            else -> null
        }

        preferencesKey?.let {
            val preferences = dataStore.data.first()
            return preferences[ preferencesKey ]
        }

        return null
    }

    override fun authStatus(): Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[isAuthenticatedKey] ?: false
    }
}