package com.ff.funum.model

import com.ff.funum.utils.Constants
import com.google.gson.annotations.SerializedName

data class LoginData (
    @SerializedName(value = Constants.LOGIN_IDENTIFIER)
    var identifier: String = "",

    @SerializedName(value = Constants.LOGIN_PWD)
    var pwd: String = "",

    var remember: Boolean = false
)