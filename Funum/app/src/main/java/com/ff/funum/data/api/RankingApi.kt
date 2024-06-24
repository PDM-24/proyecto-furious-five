package com.ff.funum.data.api

import com.ff.funum.utils.Constants
import com.google.gson.annotations.SerializedName

data class RankingRespose(
    @SerializedName(value = Constants.AUTH_RANKING)
    val ranking: List<User_ranking>
)

data class User_ranking(
    @SerializedName(value = Constants.AUTH_NOMBRE)
    val nombre: String,
    @SerializedName(value = Constants.AUTH_PUNTOS_TOTALES)
    val puntos_totales: Int,
    @SerializedName(value = Constants.AUTH_AVATAR_ACTUAL)
    val avatar_actual: String
)