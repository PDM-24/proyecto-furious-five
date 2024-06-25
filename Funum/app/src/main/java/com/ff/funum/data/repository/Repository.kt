package com.ff.funum.data.repository

import android.content.Context
import android.util.Log
import com.ff.funum.data.api.ApiClient
import com.ff.funum.data.api.ApiClient.apiService
import com.ff.funum.data.api.BuyAvatarRequest
import com.ff.funum.data.api.ChangeAvatarRequest
import com.ff.funum.data.api.LessonAPI
import com.ff.funum.data.local.datastore.DataStore
import com.ff.funum.model.Avatar
import com.ff.funum.model.User
import com.ff.funum.utils.Constants
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

    suspend fun getUser(): User {
        val token = getToken() ?: throw IllegalStateException("Token no disponible")
        Log.d("Repository", "Obteniendo usuario con token: $token")
        val user = api.getUser("Bearer $token")
        Log.d("Repository", "Respuesta de la API de usuario: $user")
        return user
    }

    suspend fun fetchAvatars(): List<Avatar> {
        val token = getToken() ?: throw IllegalStateException("Token no disponible")
        Log.d("Repository", "Obteniendo avatares con token: $token")

        try {
            val response = api.getAllAvatars("Bearer $token")
            Log.d("Repository", "Avatares obtenidos correctamente: ${response.avatar}")
            return response.avatar
        } catch (e: Exception) {
            Log.e("Repository", "Error al obtener avatares", e)
            throw e
        }
    }
    suspend fun addAvatar(avatar: Avatar): Avatar {
        val token = getToken() ?: throw IllegalStateException("Token no disponible")
        Log.d("Repository", "Añadiendo avatar con nombre: ${avatar.nombre}, imagen: ${avatar.imagen}, costo: ${avatar.costo}")
        Log.d("Repository", "URL de compra de avatar: ${Constants.API_PATH + Constants.AVATAR_PATH + Constants.BUYAVATAR_PATH}")
        try {
            val addedAvatar = api.addAvatar(avatar, "Bearer $token") // Pasar el token como cabecera
            Log.d("Repository", "Avatar añadido correctamente: $addedAvatar")
            return addedAvatar
        } catch (e: Exception) {
            Log.e("Repository", "Error al añadir avatar", e)
            throw e
        }
    }

    suspend fun buyAvatar(imagen: String, costo: Int) {
        val token = getToken() ?: throw IllegalStateException("Token no disponible")
        Log.d("Repository", "Comprando avatar con imagen: $imagen, costo: $costo")

        try {
            val request = BuyAvatarRequest(imagen, costo)
            api.buyAvatar(request, "Bearer $token")
            Log.d("Repository", "Compra de avatar exitosa")
        } catch (e: Exception) {
            Log.e("Repository", "Error al comprar avatar", e)
            throw e
        }
    }

    suspend fun changeAvatar(imagen: String){
        val token = getToken() ?: throw IllegalStateException("Token no disponible")
        Log.d("Repository", "Cambiando avatar con imagen: $imagen")

        try {
            val request = ChangeAvatarRequest(imagen)
            api.changeAvatar(request, "Bearer $token")
            Log.d("Repository", "Compra de avatar exitosa")
        } catch (e: Exception) {
            Log.e("Repository", "Error al comprar avatar", e)
            throw e
        }
    }






}



