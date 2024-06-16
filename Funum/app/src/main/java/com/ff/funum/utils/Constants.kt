package com.ff.funum.utils

object Constants {
    // API service
    // Modificar la BASE_URL para cada uno al probar con la API
    const val BASE_URL = "http://192.168.0.29:3500"
    const val API_PATH = "/api"

    const val LESSON_PATH = "/lesson"

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

    // Modificar el TOKEN ya que aun no esta el LOGIN
    const val TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2NTZmOGY4ZDFlNGY1MjcxYTA4MjAzZDIiLCJleHAiOjE3MTk3NzcxNzQsImlhdCI6MTcxODQ4MTE3NH0.vns8-EG1Zv2BdWF6nDFDCI6qKmyPllqIsmc3JB-qO_w"
}