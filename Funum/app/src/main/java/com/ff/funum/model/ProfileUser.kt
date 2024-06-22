package com.ff.funum.model

import com.ff.funum.utils.Constants
import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    val nombre: String?,
    val correo: String?,
    val puntos_canjeables: Int,
    val puntos_totales: Int = 0,
    val roles: Array<String> = arrayOf(),
    val temas: List<TemaApi> = listOf(),
    val lecciones: List<LessonApi> = listOf(),
    //val examenes: List<String>,
    val avatar_actual: String = "",
    val avatares_disponibles: Array<String> = arrayOf()
)

data class TemaApi(
    @SerializedName(value = Constants.TEMA_ID)
    val id: String? = "",
)

data class LessonApi(
    @SerializedName(value = Constants.LESSON_ID)
    val id: String = "",
    @SerializedName(value = "leccion")
    val leccion: String = ""
)