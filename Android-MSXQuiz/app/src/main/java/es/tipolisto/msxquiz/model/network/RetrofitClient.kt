package es.tipolisto.msxquiz.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient() {
    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://api.quiz.tipolisto.es/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    //     .baseUrl("http://quiz.tipolisto.es/index/Api/")
    //.baseUrl("https://dog.ceo/api/breed/")
    /*fun getAllQuiz(){
        CoroutineScope(Dispatchers.IO).launch {
            val call: Call<Person> = getRetrofit().create(QuizService::class.java).geAllList()
            val quizModelList:Person?=call.enqueue(
                call.execute())
            Log.d("Mensaje", quizModelList.toString())
        }
    }*/



}