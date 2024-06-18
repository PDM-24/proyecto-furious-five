package com.ff.funum.ui.screens

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ff.funum.data.api.ApiClient
import com.ff.funum.data.api.LessonAPI
import com.ff.funum.data.api.TopicAPI
import com.ff.funum.data.api.UpdateTopic
import com.ff.funum.data.listLessons
import com.ff.funum.data.local.datastore.DataStore
import com.ff.funum.data.repository.Repository
import com.ff.funum.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LessonsViewModel(application: Application) : AndroidViewModel(application) {
    private val api = ApiClient.apiService
    private val repository = Repository(application)

    fun getAllLessons() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getToken().collect { token ->
                    if (token != null) {
                        val response = repository.getAllLessons(token)
                        listLessons.clear()
                        listLessons.addAll(listOf(response))
                    } else {
                        Log.i("LessonsViewModel", "Token is null")
                    }
                }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.i("LessonsViewModel", e.toString())
                    }

                    else -> {
                        Log.i("LessonsViewModel", e.toString())
                    }
                }
            }
        }
    }

    //Gestiona la informacion de Update topic screen
    var updatedTopic :TopicAPI= TopicAPI()

    //Update topic
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun updateTopic(topic: TopicAPI, idLesson:String, idTopic:String="",token:String) {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = topic.id?.let {
                    UpdateTopic(topic.nombre,topic.contenido,topic.ponderacion,topic.visibility,topic.imagen,
                        it,idLesson)
                }?.let { api.updateTopic(it, idTopic, "Bearer $token") }
                Log.i("MainViewModel",response.toString())
            }catch (e:Exception){
                when(e){
                    is retrofit2.HttpException -> {
                        e.message?.let { Log.i("MainViewmodel", it) }
                    }
                    else -> {
                        Log.i("MainViewModel", e.toString())
                    }
                }
            }
        }
    }
    //Toggle topic visibility
    fun toggleTopicVisibility(idTopic:String="",token:String) {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = api.toggleTopicVisibility( idTopic, "Bearer $token")
                Log.i("MainViewModel",response.toString())
            }catch (e:Exception){
                when(e){
                    is retrofit2.HttpException -> {
                        e.message?.let { Log.i("MainViewmodel", it) }
                    }
                    else -> {
                        Log.i("MainViewModel", e.toString())
                    }
                }
            }
        }
    }
}