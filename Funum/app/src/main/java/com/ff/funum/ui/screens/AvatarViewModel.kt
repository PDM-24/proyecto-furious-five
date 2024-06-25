package com.ff.funum.ui.screens
import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ff.funum.data.repository.Repository
import com.ff.funum.model.Avatar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AvatarViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application.applicationContext)

    private val _avatars = MutableLiveData<List<Avatar>>()
    val avatars: LiveData<List<Avatar>> get() = _avatars

    fun addAvatar(avatar: Avatar, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val addedAvatar = repository.addAvatar(avatar)
                onSuccess()
                withContext(Dispatchers.Main) {
                    Toast.makeText(getApplication(), "El avatar ha sido creado correctamente", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                onError(e.message ?: "Error al agregar avatar")
            }
        }
    }

    fun fetchAvatars() {
        viewModelScope.launch {
            try {
                val avatars = repository.fetchAvatars()
                _avatars.postValue(avatars)
            } catch (e: Exception) {
                Log.e("AvatarViewModel", "Error al obtener avatares", e)
            }
        }
    }

    fun buyAvatar(imagen: String, costo: Int, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                repository.buyAvatar(imagen, costo)
                onSuccess()
                withContext(Dispatchers.Main) {
                    Toast.makeText(getApplication(), "Avatar comprado correctamente", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                onError(e.message ?: "Error al comprar avatar")
            }
        }
    }

}


