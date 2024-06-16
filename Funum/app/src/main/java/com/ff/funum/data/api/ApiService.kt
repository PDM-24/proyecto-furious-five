package com.ff.funum.data.api

import com.ff.funum.utils.Constants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface APIService {
    // Select Lessons
    @Headers(value = ["Content-type: application/json"])
    @GET(value = Constants.API_PATH + Constants.LESSON_PATH + Constants.ALL_LESSONS)
    suspend fun viewAllLessons(@Header("Authorization") token: String) : LessonAPI

    //Update Topic
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH+Constants.Topic_Path+Constants.Update_Topic_Path+"{id}")
    suspend fun updateTopic(@Body topic: UpdateTopic, @Path("id", encoded = false) idTopic:String, @Header("authorization") token:String):TopicAPI

    //Toggle topic visibility
    @Headers(value = ["Content-Type: application/json"])
    @PATCH(value = Constants.API_PATH+Constants.Topic_Path+Constants.Toggle_Topic_Visibility_Path+"{id}")
    suspend fun toggleTopicVisibility(@Path("id", encoded = false) idTopic:String,@Header("authorization") token:String):TopicAPI

}