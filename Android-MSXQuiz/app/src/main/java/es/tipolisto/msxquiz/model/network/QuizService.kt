package es.tipolisto.msxquiz.model.network


import es.tipolisto.msxquiz.model.*
import retrofit2.Call

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url


interface QuizService {


    //https://quiz.tipolisto.es/api/getQuizModelList.php
    //fun getQuizList(@Url url:String):Call<List<Quiz>>
    @GET("getQuizModelList.php")
    suspend fun getQuizList(): Response<List<Quiz>>

    @GET("getImage.php/{idImage}")
    suspend fun getImage(@Path ("idImage") idImage:String): Response<Image>

    @POST("setScore.php")
    @FormUrlEncoded
    suspend fun saveScore(
        @Field("user") name: String,
        @Field("password") password: String,
        @Field("score") score: Int
    ): Response<String>

}