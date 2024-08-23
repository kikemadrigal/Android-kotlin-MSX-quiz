package es.tipolisto.msxquiz.model.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient() {
    companion object{
        fun getRetrofit():Retrofit{
            return Retrofit.Builder()
                .baseUrl("https://quiz.tipolisto.es/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        fun getRetrofitApi():QuizService{
            val api = getRetrofit().create(QuizService::class.java)
            return api
        }
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