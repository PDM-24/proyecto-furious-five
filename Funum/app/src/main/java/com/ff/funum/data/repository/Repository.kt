package com.ff.funum.data.repository

import android.content.Context
import com.ff.funum.data.api.ApiClient
import com.ff.funum.data.api.LessonAPI
import com.ff.funum.data.local.datastore.DataStore
import kotlinx.coroutines.flow.Flow

class Repository(private val context: Context) {
    private val api = ApiClient.apiService
    private val dataStore = DataStore(context)

    fun getToken(): Flow<String?> {
        return dataStore.getToken(context)
    }

    suspend fun getAllLessons(token: String): LessonAPI {
        return api.viewAllLessons("Bearer $token")
    }
}