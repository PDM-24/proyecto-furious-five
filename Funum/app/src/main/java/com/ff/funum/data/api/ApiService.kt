package com.ff.funum.data.api

import com.ff.funum.model.Avatar
import com.ff.funum.model.AvatarsResponse
import com.ff.funum.model.LoginData
import com.ff.funum.model.User
import com.ff.funum.utils.Constants
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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

    //Delete topic
    @Headers(value = ["Content-Type: application/json"])
    @HTTP(method = "DELETE", path = Constants.API_PATH+Constants.Topic_Path+Constants.Delete_Topic_Path+"{id}", hasBody = true)
    suspend fun deleteTopic(@Body idLesson: DeleteTopic,@Path("id", encoded = false) idTopic:String,@Header("authorization") token:String):ApiResponseSuccessful


    //Update Lesson
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH+Constants.LESSON_PATH+Constants.Update_Lesson_Path+"{id}")
    suspend fun updateLesson(@Body lesson: Lessons, @Path("id", encoded = false) idLesson:String, @Header("authorization") token:String):Lessons

    //Toggle lesson visibility
    @Headers(value = ["Content-Type: application/json"])
    @PATCH(value = Constants.API_PATH+Constants.LESSON_PATH+Constants.Toggle_Lesson_Visibility_Path+"{id}")
    suspend fun toggleLessonVisibility(@Path("id", encoded = false) idLesson:String,@Header("authorization") token:String):Lessons

    //Delete lesson
    @Headers(value = ["Content-Type: application/json"])
    @DELETE(value = Constants.API_PATH+Constants.LESSON_PATH+Constants.Delete_Lesson_Path+"{id}")
    suspend fun deleteLesson(@Path("id", encoded = false) idLesson:String,@Header("authorization") token:String):ApiResponseSuccessful

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

    //Register user
    @Headers(value = ["Content-type: application/json"])
    @POST(value = Constants.API_PATH + Constants.AUTH_PATH + Constants.REGISTER_PATH)
    suspend fun registerUser(@Body user : RegisterApi) : ApiResponseSuccessful

    // Login user
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.AUTH_PATH + Constants.LOGIN_PATH)
    suspend fun loginUser(@Body data: LoginData) : LoginResponse

    // Get User
    @Headers(value = ["Content-type: application/json"])
    @GET(value = Constants.API_PATH + Constants.AUTH_PATH + Constants.WHOAMI_PATH)
    suspend fun getUser(@Header("Authorization") token: String): User

    //Get Avatar
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = Constants.API_PATH + Constants.AVATAR_PATH)
    suspend fun getAllAvatars(@Header("Authorization") token: String): AvatarsResponse
    //Add Avatar
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.AVATAR_PATH)
    suspend fun addAvatar(
        @Body avatar: Avatar,
        @Header("Authorization") token: String
    ): Avatar

//    @Headers("Content-Type: application/json")
//    @PATCH(Constants.API_PATH + Constants.AUTH_PATH + Constants.BUYAVATAR_PATH)
//    suspend fun buyAvatar(
//        @Body avatar: Avatar,
//        @Header("Authorization") token: String
//    )
@Headers(value = ["Content-Type: application/json"])
@PATCH(Constants.API_PATH + Constants.AUTH_PATH + Constants.BUYAVATAR_PATH)
suspend fun buyAvatar(
    @Body request: BuyAvatarRequest,
    @Header("Authorization") token: String
)

@Headers(value = ["Content-Type: application/json"])
@PATCH(Constants.API_PATH + Constants.AUTH_PATH + Constants.CHANGEAVATAR_PATH)
suspend fun changeAvatar(
    @Body request: ChangeAvatarRequest,
    @Header("Authorization") token: String
)



    // Begin Topic
    @Headers(
        value = ["Content-Type: application/json"]
    )
    @PATCH(value = "${Constants.API_PATH}${Constants.Topic_Path}${Constants.START_TOPIC}/{id}")
    suspend fun beginTopic(
        @Header("Authorization") token: String,
        @Path("id") topicId: String?
    ): BeginTopicResponse

    // End Topic
    @Headers(
        value = ["Content-Type: application/json"]
    )
    @PATCH(value = "${Constants.API_PATH}${Constants.Topic_Path}${Constants.FINISH_TOPIC}/{id}")
    suspend fun endTopic(
        @Header("Authorization") token: String,
        @Path("id") topicId: String?
    ): EndTopicResponse

    @Headers(
        value = ["Content-Type: application/json"]
    )
    @PATCH(value = "${Constants.API_PATH}${Constants.LESSON_PATH}${Constants.START_LESSON}/{id}")
    suspend fun beginLesson(
        @Header("Authorization") token: String,
        @Path("id") topicId: String?
    ): BeginEndLessonResponse

    @Headers(
        value = ["Content-Type: application/json"]
    )
    @PATCH(value = "${Constants.API_PATH}${Constants.LESSON_PATH}${Constants.FINISH_LESSON}/{id}")
    suspend fun endLesson(
        @Header("Authorization") token: String,
        @Path("id") topicId: String?
    ): BeginEndLessonResponse

    @Headers(
        value = ["Content-Type: application/json"]
    )
    @GET(value = "${Constants.API_PATH}${Constants.AUTH_PATH}${Constants.AUTH_RANKING_PATH}")
    suspend fun getRanking(
        @Header("Authorization") token: String
    ): RankingRespose

    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH+Constants.EXAM_ROUTE+Constants.SAVE_EXAM_PATH)
    suspend fun saveExam(
        @Body adminSaveExam: AdminSaveExam,
        @Header("authorization") token:String
    ): ExamApi
}