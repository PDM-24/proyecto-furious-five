package com.ff.funum.data.api

import  com.ff.funum.utils.Constants
import com.google.gson.annotations.SerializedName

data class LessonAPI(
    @SerializedName(value = "leccion")
    val leccion : MutableList<Lessons>
)

data class Lessons(
    @SerializedName(value = Constants.LESSON_ID)
    val id : String? = null,

    @SerializedName(value = Constants.LESSON_VISIBILITY)
    val visibility : Boolean = true,

    @SerializedName(value = Constants.LESSON_AREA)
    val area_estudio : String = "",

    @SerializedName(value = Constants.LESSON_NAME)
    val nombre : String = "",

    @SerializedName(value = Constants.LESSON_IMAGE)
    val imagen : String = "",

    @SerializedName(value = Constants.LESSON_TOPIC_LIST)
    val topicList : MutableList<TopicAPI> = arrayListOf()
)

data class TopicAPI(
    @SerializedName(value = Constants.TOPIC_ID)
    val id : String? = null,

    @SerializedName(value = Constants.TOPIC_VISIBILITY)
    val visibility : Boolean = true,

    @SerializedName(Constants.TOPIC_IMAGE)
    val imagen: List<String> = listOf(),

    @SerializedName(value = Constants.TOPIC_CONTENT)
    val contenido : String = "",

    @SerializedName(value = Constants.TOPIC_NAME)
    val nombre : String = "",

    @SerializedName(value = Constants.TOPIC_PONDERATION)
    val ponderacion : Int = 0
)
data class UpdateTopic(
    @SerializedName(value = Constants.TOPIC_NAME)
    val nombre :String = "",
    @SerializedName(value = Constants.TOPIC_CONTENT)
    val contenido :String = "",
    @SerializedName(value = Constants.TOPIC_PONDERATION)
    val ponderacion :Int = 0,
    @SerializedName(value = Constants.TOPIC_VISIBILITY)
    val visibilidad :Boolean = false,
    @SerializedName(value = Constants.TOPIC_IMAGE)
    val imagen :List<String> = arrayListOf(""),
    @SerializedName(value = Constants.TOPIC_ID)
    val _id :String ="",
    @SerializedName(value = "idLeccion")
    val idLeccion :String =""

)