package com.ff.funum.data.api

import com.google.gson.annotations.SerializedName

data class ChangeAvatarRequest(
    @SerializedName("avatar") val imagen: String
)