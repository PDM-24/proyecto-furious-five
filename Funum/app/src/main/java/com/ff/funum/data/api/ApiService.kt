package com.ff.funum.data.api

import com.ff.funum.utils.Constants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.Path

interface APIService {
    // Select Lessons
    @Headers(value = ["Content-type: application/json"])
    @GET(value = Constants.API_PATH + Constants.LESSON_PATH + Constants.ALL_LESSONS)
    suspend fun viewAllLessons(@Header("Authorization") token: String) : LessonAPI

    @Headers(
        value = ["Content-Type: application/json"]
    )
    @GET(value = "${Constants.API_PATH}${Constants.EXAM_ROUTE}/{id}")
    suspend fun getExam(
        @Header("Authorization") token: String,
        @Path("id") examId: String
    ): ExamResponse

    @Headers(
        value = ["Content-Type: application/json"]
    )
    @PATCH(value = "${Constants.API_PATH}${Constants.EXAM_ROUTE}${Constants.START_EXAM}/{id}")
    suspend fun beginExam(
        @Header("Authorization") token: String,
        @Path("id") examId: String
    ): BeginOrFinish_Exam_Response

    @Headers(
        value = ["Content-Type: application/json"]
    )
    @PATCH(value = "${Constants.API_PATH}${Constants.EXAM_ROUTE}${Constants.FINISH_EXAM}/{id}")
    suspend fun finishExam(
        @Header("Authorization") token: String,
        @Path("id") examId: String,
        @Body endExamBody: EndExamBody
    ): BeginOrFinish_Exam_Response
}