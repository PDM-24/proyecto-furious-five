package com.ff.funum.data.api

import com.google.gson.annotations.SerializedName

data class BuyAvatarRequest(
    @SerializedName("imagen") val imagen: String,
    @SerializedName("costo") val costo: Int
)

