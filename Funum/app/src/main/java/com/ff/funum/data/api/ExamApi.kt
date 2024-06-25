package com.ff.funum.data.api

import com.ff.funum.utils.Constants
import com.google.gson.annotations.SerializedName

data class ExamResponse(
    @SerializedName(value = "exam")
    val exam: ExamApi
)

data class ExamApi(
    @SerializedName(value = Constants.EXAM_ID)
    val id: String = "",

    @SerializedName(value = Constants.EXAM_VISIBILIDAD)
    val visibilidad: Boolean = false,

    @SerializedName(value = Constants.EXAM_TEMAS)
    val temas: MutableList<TemaApi> = arrayListOf(),

    @SerializedName(value = Constants.EXAM_TIPO_EXAMEN)
    val tipo_Examen: Tipo_Examen = Tipo_Examen(),

    @SerializedName(value = Constants.EXAM_PREGUNTA_OPCION_MULTIPLE)
    val preguntas_opcion_multiple: MutableList<Pregunta_opcion_multiple_Api> = arrayListOf(),

    @SerializedName(value = Constants.EXAM_PREGUNTA_MATCH)
    val preguntas_match: MutableList<Pregunta_match_api> = arrayListOf(),

    @SerializedName(value = "nombre")
    val nombre: String = ""
)

data class TemaApi(
    @SerializedName(value = Constants.TEMA_ID)
    val id: String = "",

    @SerializedName(value = Constants.TEMA_NOMBRE)
    val nombre: String = ""
)

data class Pregunta_opcion_multiple_Api(
    @SerializedName(value = Constants.PREGUNTA_OPCION_MULTIPLE_ID)
    val id: String = "",

    @SerializedName(value = Constants.PREGUNTA_OPCION_MULTIPLE_IMAGEN)
    val imagen: String = "",

    @SerializedName(value = Constants.PREGUNTA_OPCION_MULTIPLE_ENUNCIADO)
    val enunciado: String= "",

    @SerializedName(value = Constants.PREGUNTA_OPCION_MULTIPLE_TEMA)
    val tema: TemaApi = TemaApi("",""),

    @SerializedName(value = Constants.PREGUNTA_OPCION_MULTIPLE_RESPUESTA)
    val respuestas: MutableList<Respuesta_opcion_multiple_api> = arrayListOf()
)

data class Respuesta_opcion_multiple_api(
    @SerializedName(value = Constants.RESPUESTA_OPCION_MULTIPLE_ID)
    val id: String = "",

    @SerializedName(value = Constants.RESPUESTA_OPCION_MULTIPLE_DESCRIPCION)
    val descripcion: String="",

    @SerializedName(value = Constants.RESPUESTA_OPCION_MULTIPLE_CORRECTA)
    val correcta: Boolean,

    @SerializedName(value = Constants.RESPUESTA_OPCION_MULTIPLE_IMAGEN)
    val imagen: String = ""
)

data class Pregunta_match_api(
    @SerializedName(value = Constants.PREGUNTA_MATCH_ID)
    val id: String = "",

    @SerializedName(value = Constants.PREGUNTA_MATCH_IMAGEN)
    val imagen: String = "",

    @SerializedName(value = Constants.PREGUNTA_MATCH_ENUNCIADO)
    val enunciado: String,

    @SerializedName(value = Constants.PREGUNTA_MATCH_TEMA)
    val tema: TemaApi,

    @SerializedName(value = Constants.PREGUNTA_MATCH_RESPUESTA)
    val respuestas: MutableList<Respuesta_match_api>,
)

data class Respuesta_match_api(
    @SerializedName(value = Constants.RESPUESTA_MATCH_ID)
    val id: String = "",

    @SerializedName(value = Constants.RESPUESTA_MATCH_DESCRIPCION)
    val descripcion: String,

    @SerializedName(value = Constants.RESPUESTA_MATCH_TIPO)
    val tipo: Int,

    @SerializedName(value = Constants.RESPUESTA_MATCH_IMAGEN)
    val imagen: String = ""
)

data class Tipo_Examen(
    @SerializedName(value = Constants.TIPO_EXAMEN_ID)
    val id: String = "",

    @SerializedName(value = Constants.TIPO_EXAMEN_TIPO)
    val tipo: String = "",

    @SerializedName(value = Constants.TIPO_EXAMEN_PONDERACION)
    val ponderacion: Int = 0,
)

data class BeginOrFinish_Exam_Response(
    @SerializedName(value = "examenes")
    val examenes: MutableList<Examenes>
)

data class Examenes(
    @SerializedName(value = "examen")
    val examen: String = "",

    @SerializedName(value = "fecha_hora_inicio")
    val fecha_hora_inicio: String = "",
)

data class EndExamBody(
    @SerializedName(value = "calificacion")
    val calificacion: Int,

    @SerializedName(value = "fecha_hora_inicio")
    val fecha_hora_inicio: String? = "2023-11-29T01:28:21.907Z"
)

data class AdminSaveExam(
    @SerializedName(value = Constants.SAVE_EXAM_NOMBRE)
    val nombre: String = "",
    @SerializedName(value = Constants.SAVE_EXAM_DESCRIPCION)
    val descripcion: String = "",
    @SerializedName(value = Constants.SAVE_EXAM_PONDERACION)
    val ponderacion: String = "",
    @SerializedName(value = Constants.SAVE_EXAM_TIPO)
    val tipo: String = "evaluado",
    @SerializedName(value = Constants.SAVE_EXAM_TEMAID)
    val temaId: String = "",
    @SerializedName(value = Constants.SAVE_EXAM_IDLECCION)
    val idLeccion: String? = ""
)
