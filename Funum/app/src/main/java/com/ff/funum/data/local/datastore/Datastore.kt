package com.ff.funum.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(name = "settings")

class DataStore (private val context: Context){

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
}