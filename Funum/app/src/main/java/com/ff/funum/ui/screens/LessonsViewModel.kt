package com.ff.funum.ui.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ff.funum.data.api.ApiClient
import com.ff.funum.data.api.TopicAPI
import com.ff.funum.data.api.UpdateTopic
import com.ff.funum.data.listLessons
import com.ff.funum.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LessonsViewModel: ViewModel() {
    private val api = ApiClient.apiService

    fun getAllLessons() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.viewAllLessons("Bearer ${Constants.TOKEN}")

                listLessons.clear()
                listLessons.add(response)

            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.i("MainViewModel", e.toString())
                    }

                    else -> {
                        Log.i("MainViewModel", e.toString())
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