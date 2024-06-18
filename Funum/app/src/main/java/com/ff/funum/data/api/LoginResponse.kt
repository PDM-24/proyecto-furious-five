package com.ff.funum.data.api

import com.ff.funum.utils.Constants
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName(value = Constants.LOGIN_TOKEN)
    var token: String
)
