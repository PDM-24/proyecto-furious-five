package com.ff.funum.ui.screens

import android.app.Application
import android.content.Context
import androidx.compose.runtime.State
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ff.funum.data.repository.Repository
import com.ff.funum.model.User
import com.ff.funum.data.api.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application.applicationContext)

    private val _username = MutableStateFlow<String>("Cargando...")
    private val _points = MutableStateFlow<Int?>(null)
    private val _roles = MutableStateFlow<Array<String>>(arrayOf())

    val points: StateFlow<Int?> get() = _points
    val username: StateFlow<String> get() = _username
    val roles: StateFlow<Array<String>> get() = _roles

    fun fetchUsername() {
        viewModelScope.launch {
            try {
                val user = repository.getUser()
                _username.value = user.nombre ?: "Nombre no disponible"
                _points.value = user.puntos_canjeables
                _roles.value = user.roles
                Log.d("ProfileViewModel", "Usuario obtenido: ${user.nombre}")
            } catch (e: Exception) {
                _username.value = "Error al cargar"
                _points.value = null
                Log.e("ProfileViewModel", "Error obteniendo el usuario", e)
            }
        }
    }
}



