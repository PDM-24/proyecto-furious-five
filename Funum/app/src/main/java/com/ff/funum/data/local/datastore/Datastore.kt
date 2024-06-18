package com.ff.funum.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

class DataStore (private val context: Context){
    private val TOKEN_KEY = stringPreferencesKey("token")

    suspend fun saveData(id: String, date: String) {
        val dateKey = stringPreferencesKey(id)
        context.dataStore.edit { preferences ->
            preferences[dateKey] = date
        }
    }

    suspend fun deleteNamePreferences(id: String) {
        val dateKey = stringPreferencesKey(id)
        context.dataStore.edit {preferences ->
            preferences.remove(dateKey)
        }
    }

    suspend fun getDate(id: String): String? {
        val dateKey = stringPreferencesKey(id)
        val preferences = context.dataStore.data.first()
        return preferences[dateKey]
    }

    suspend fun saveToken(context: Context, token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun getToken(context: Context): String? {
        val preferences = context.dataStore.data.first()
        return preferences[TOKEN_KEY]
    }

    suspend fun clearToken(context: Context) {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }
}