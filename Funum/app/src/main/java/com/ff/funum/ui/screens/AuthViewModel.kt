package com.ff.funum.ui.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ff.funum.data.api.ApiClient
import com.ff.funum.data.api.RegisterApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AuthViewModel : ViewModel() {
    private val api = ApiClient.apiService

    private val _registerSuccess = MutableLiveData<Boolean>()
    val registerSuccess: LiveData<Boolean> get() = _registerSuccess

    // Register User
    fun registerUser(user: RegisterApi){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = api.registerUser(user)
                Log.i("MainViewModel", response.message)
                _registerSuccess.postValue(true)
            }
            catch (e: Exception){
                when (e){
                    is HttpException -> {
                        Log.i("MainViewModel", e.toString())
                    }
                    else -> {
                        Log.i("MainViewModel", e.toString())
                    }
                }
                _registerSuccess.postValue(false)
            }
        }
    }
}