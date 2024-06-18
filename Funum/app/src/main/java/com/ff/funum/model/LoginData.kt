package com.ff.funum.model

import com.google.gson.annotations.SerializedName

data class LoginData (
    @SerializedName(value = "identifier")
    var identifier: String = "",

    @SerializedName(value = "password")
    var pwd: String = ""
){
    fun isNotEmpty(): Boolean {
        return identifier.isNotEmpty() && pwd.isNotEmpty()
    }
}