package com.ff.funum.data.api

import com.ff.funum.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface APIService {
    // Select Lessons
    @Headers(value = ["Content-type: application/json"])
    @GET(value = Constants.API_PATH + Constants.LESSON_PATH + Constants.ALL_LESSONS)
    suspend fun viewAllLessons(@Header("Authorization") token: String) : LessonAPI
}