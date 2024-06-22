package com.ff.funum.model

data class Exam(
    val visibilidad: Boolean = false,
    val temas: MutableList<Tema> = arrayListOf(),
    val ponderacion: Int = 0,
    val preguntas_opcion_multiple: MutableList<Pregunta_opcion_multiple> = arrayListOf(),
    val preguntas_match: MutableList<Pregunta_match> = arrayListOf(),
    val nombre: String = ""
)

data class Tema(
    val id: String = "",
    val nombre: String = ""
)

data class Pregunta_opcion_multiple(
    val id: String = "",
    val imagen: String = "",
    val enunciado: String,
    val tema: Tema,
    val respuestas: MutableList<Respuesta_opcion_multiple>
)

data class Respuesta_opcion_multiple(
    val id: String = "",
    val descripcion: String,
    val correcta: Boolean,
    val imagen: String = ""
)

data class Pregunta_match(
    val id: String = "",
    val imagen: String = "",
    val enunciado: String,
    val tema: Tema,
    val respuestas: MutableList<Respuesta_match>,
)

data class Respuesta_match(
    val id: String = "",
    val descripcion: String,
    val tipo: Int,
    val imagen: String = ""
)

data class SortedQuestion(
    val type: String,
    val question: Any
)