package com.ff.funum.ui.screens

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ff.funum.data.api.ApiClient
import com.ff.funum.data.api.RegisterApi
import com.ff.funum.data.local.datastore.DataStore
import com.ff.funum.model.LoginData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val api = ApiClient.apiService
    private val _registerSuccess = MutableLiveData<Boolean>()
    val registerSuccess: LiveData<Boolean> get() = _registerSuccess
    private val _uiState = MutableStateFlow<UiStateAuth>(UiStateAuth.Ready)
    val uiState: StateFlow<UiStateAuth> = _uiState
    private val viewModelContext by lazy { getApplication<Application>().applicationContext }
    val dataStore = DataStore(viewModelContext)
    fun setStateToReady (){
        _uiState.value = UiStateAuth.Ready
    }

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

    fun checkLogin(loginData : LoginData){
        viewModelScope.launch (Dispatchers.IO){
            try{
                _uiState.value = UiStateAuth.Loading
                val result = ApiClient.apiService.loginUser(loginData)
                Log.i("MainViewModel", result.toString())
                setRememberMe(loginData.remember)
                _uiState.value = UiStateAuth.Success(result.token)
                dataStore.saveToken(viewModelContext, result.token)
            }
            catch (e : HttpException){
                Log.i("MainViewModel", "ERROR! $e")
                _uiState.value = UiStateAuth.Error(400)
            }
        }
    }
    private fun setRememberMe(value: Boolean){
        viewModelScope.launch (Dispatchers.IO){
            dataStore.saveRememberMe(value)
        }
    }
}

sealed class UiStateAuth {
    data class Success(val token: String): UiStateAuth()
    data class Error(val code: Int) : UiStateAuth()
    object Ready : UiStateAuth()
    object Loading : UiStateAuth()
}