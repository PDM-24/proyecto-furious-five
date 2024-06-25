package com.ff.funum.utils

object Constants {
    // API service
    // Modificar la BASE_URL para cada uno al probar con la API


    const val BASE_URL = "http://192.168.0.29:3500"


    const val API_PATH = "/api"
    const val WHOAMI_PATH = "/whoami"

    const val LESSON_PATH = "/lesson"

    const val AUTH_PATH = "/auth"
    const val REGISTER_PATH = "/register"
    const val LOGIN_PATH = "/login"
    const val AUTH_RANKING_PATH = "/ranking"

    const val AUTH_NOMBRE = "nombre"
    const val AUTH_PUNTOS_TOTALES = "puntos_totales"
    const val AUTH_AVATAR_ACTUAL = "avatar_actual"
    const val AUTH_RANKING = "ranking"

    const val ALL_LESSONS = "/"
    // Update Lesson
    const val Update_Lesson_Path="/"

    //Toggle Lesson visibilty
    const val Toggle_Lesson_Visibility_Path="/visibility/"

    //Delete Lesson
    const val Delete_Lesson_Path="/"

    const val LESSON_ID = "_id"
    const val LESSON_VISIBILITY = "visibilidad"
    const val LESSON_AREA = "area_estudio"
    const val LESSON_NAME = "nombre"
    const val LESSON_IMAGE = "imagen"
    const val LESSON_TOPIC_LIST = "temas"
    const val LESSON_EXAM_LIST = "examenes"

    const val TOPIC_ID = "_id"
    const val TOPIC_VISIBILITY = "visibilidad"
    const val TOPIC_IMAGE = "imagen"
    const val TOPIC_CONTENT = "contenido"
    const val TOPIC_NAME = "nombre"
    const val TOPIC_PONDERATION = "ponderacion"

    //Toggle Topic visibilty
    const val Toggle_Topic_Visibility_Path="/visibility/"

    //Delete Topic
    const val Delete_Topic_Path="/"
    // Update Topic
    const val Update_Topic_Path="/"
    //Api response
    const val ResponseSuccessful="message"
    const val ResponseError="error"
    const val Topic_Path="/tema"

    //Constantes para los examenes

    const val EXAM_ROUTE = "/exam"

    // Start Exam
    const val START_EXAM = "/begin"
    // Finish Exam
    const val FINISH_EXAM = "/end"

    const val SAVE_EXAM_PATH = "/6562d5f446fa0e9cba08a733"

    const val SAVE_EXAM_NOMBRE = "nombre"
    const val SAVE_EXAM_DESCRIPCION = "descripcion"
    const val SAVE_EXAM_PONDERACION = "ponderacion"
    const val SAVE_EXAM_TIPO = "tipo"
    const val SAVE_EXAM_TEMAID = "temaId"
    const val SAVE_EXAM_IDLECCION = "idLeccion"

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

    const val USER_ID = "_id"
    const val USER_NAME = "nombre"
    const val USER_EMAIL = "correo"
    const val USER_PWD = "password"

    const val LOGIN_IDENTIFIER = "identifier"
    const val LOGIN_PWD = "password"
    const val LOGIN_TOKEN = "token"

//    Profile
    const val USER_PATH = "user"

    const val START_TOPIC= "/begin"
    const val FINISH_TOPIC = "/end"
    const val START_LESSON= "/begin"
    const val FINISH_LESSON = "/end"
}