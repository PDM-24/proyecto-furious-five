package com.ff.funum.model

data class UserDataModel(
    val id: Int,
    val nombre: String,
    val correo: String,
    val imagen: Int,
    var puntos: Int
)
