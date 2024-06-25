package com.ff.funum.data.api

import com.google.gson.annotations.SerializedName

data class BuyAvatarRequest(
    @SerializedName("avatar") val imagen: String,
    @SerializedName("puntos") val costo: Int
)

