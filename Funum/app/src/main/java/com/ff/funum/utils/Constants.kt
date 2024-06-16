package com.ff.funum.utils

object Constants {
    // API service
    // Modificar la BASE_URL para cada uno al probar con la API
    const val BASE_URL = "http://192.168.1.2:3500"
    const val API_PATH = "/api"

    const val LESSON_PATH = "/lesson"

    const val ALL_LESSONS = "/"

    const val LESSON_ID = "_id"
    const val LESSON_VISIBILITY = "visibilidad"
    const val LESSON_AREA = "area_estudio"
    const val LESSON_NAME = "nombre"
    const val LESSON_IMAGE = "imagen"
    const val LESSON_TOPIC_LIST = "temas"

    const val TOPIC_ID = "_id"
    const val TOPIC_VISIBILITY = "visibilidad"
    const val TOPIC_IMAGE = "imagen"
    const val TOPIC_CONTENT = "contenido"
    const val TOPIC_NAME = "nombre"
    const val TOPIC_PONDERATION = "ponderacion"

    //Constantes para los examenes

    const val EXAM_ROUTE = "/exam"

    // Start Exam
    const val START_EXAM = "/begin"
    // Finish Exam
    const val FINISH_EXAM = "/end"

    const val EXAM_ID = "_id"
    const val EXAM_VISIBILIDAD = "visibilidad"
    const val EXAM_TEMAS = "temas"
    const val EXAM_TIPO_EXAMEN = "tipo_Examen"
    const val EXAM_PREGUNTA_OPCION_MULTIPLE = "Pregunta_opcion_multiple"
    const val EXAM_PREGUNTA_MATCH = "Pregunta_match"

    const val TEMA_ID = "_id"
    const val TEMA_NOMBRE = "nombre"

    const val PREGUNTA_OPCION_MULTIPLE_ID = "_id"
    const val PREGUNTA_OPCION_MULTIPLE_IMAGEN = "imagen"
    const val PREGUNTA_OPCION_MULTIPLE_ENUNCIADO = "enunciado"
    const val PREGUNTA_OPCION_MULTIPLE_TEMA = "tema"
    const val PREGUNTA_OPCION_MULTIPLE_RESPUESTA = "respuesta"

    const val RESPUESTA_OPCION_MULTIPLE_ID = "_id"
    const val RESPUESTA_OPCION_MULTIPLE_IMAGEN = "imagen"
    const val RESPUESTA_OPCION_MULTIPLE_CORRECTA = "correcta"
    const val RESPUESTA_OPCION_MULTIPLE_DESCRIPCION = "descripcion"

    const val PREGUNTA_MATCH_ID = "_id"
    const val PREGUNTA_MATCH_IMAGEN = "imagen"
    const val PREGUNTA_MATCH_ENUNCIADO = "enunciado"
    const val PREGUNTA_MATCH_TEMA = "tema"
    const val PREGUNTA_MATCH_RESPUESTA = "respuesta"

    const val RESPUESTA_MATCH_ID = "_id"
    const val RESPUESTA_MATCH_IMAGEN = "imagen"
    const val RESPUESTA_MATCH_TIPO = "tipo"
    const val RESPUESTA_MATCH_DESCRIPCION = "descripcion"

    const val TIPO_EXAMEN_ID = "_id"
    const val TIPO_EXAMEN_TIPO = "tipo"
    const val TIPO_EXAMEN_PONDERACION = "ponderacion"

    // Modificar el TOKEN ya que aun no esta el LOGIN
    const val TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2NTZmOGY4ZDFlNGY1MjcxYTA4MjAzZDIiLCJleHAiOjE3MTk3NzcxNzQsImlhdCI6MTcxODQ4MTE3NH0.vns8-EG1Zv2BdWF6nDFDCI6qKmyPllqIsmc3JB-qO_w"
}