package com.ff.funum.data.api

import com.ff.funum.utils.Constants
import com.google.gson.annotations.SerializedName

data class RegisterApi(
    @SerializedName(value = Constants.USER_ID)
    val id : String? = null,

    @SerializedName(value = Constants.USER_NAME)
    val nombre : String = "",

    @SerializedName(value = Constants.USER_EMAIL)
    val correo : String = "",

    @SerializedName(value = Constants.USER_PWD)
    val password : String = ""
)
