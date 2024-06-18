package com.ff.funum.data.repository

import android.content.Context
import com.ff.funum.data.api.ApiClient
import com.ff.funum.data.api.LessonAPI
import com.ff.funum.data.local.datastore.DataStore
import kotlinx.coroutines.flow.Flow

class Repository(private val context: Context) {
    private val api = ApiClient.apiService
    private val dataStore = DataStore(context)

    suspend fun getToken(): String? {
        return dataStore.getToken(context)
    }

    suspend fun getAllLessons(token: String): LessonAPI {
        return api.viewAllLessons("Bearer $token")
    }

    suspend fun getDate(examId: String): String?{
        return dataStore.getDate(examId)
    }

    suspend fun saveData(id: String, date: String){
        return dataStore.saveData(id, date)
    }

    suspend fun deleteNamePreferences(id: String){
        return dataStore.deleteNamePreferences(id)
    }
}