package es.tipolisto.msxquiz.model.network


import es.tipolisto.msxquiz.model.*
import retrofit2.Call
import retrofit2.http.*


interface QuizService {

    @GET("getQuizModelList")
    //http://api.quiz.tipolisto.es/getQuizModelList
    //fun getQuizList(@Url url:String):Call<List<Quiz>>
    fun getQuizList():Call<List<Quiz>>

    @GET("")
    fun getImage(@Url url:String):Call<Image>

    @POST("setScore")
    @FormUrlEncoded
    fun saveScore(
        @Field("user") name: String,
        @Field("password") password: String,
        @Field("score") score: Int
    ): Call<String>

}