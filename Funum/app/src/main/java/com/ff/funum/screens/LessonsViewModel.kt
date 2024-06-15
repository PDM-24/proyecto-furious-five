package com.ff.funum.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ff.funum.data.api.ApiClient
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
}