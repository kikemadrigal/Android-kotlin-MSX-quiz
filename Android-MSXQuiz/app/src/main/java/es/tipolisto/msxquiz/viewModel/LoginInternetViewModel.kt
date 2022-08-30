package es.tipolisto.msxquiz.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.tipolisto.msxquiz.model.network.QuizService
import es.tipolisto.msxquiz.model.network.RetrofitClient
import kotlin.concurrent.thread

class LoginInternetViewModel:ViewModel() {
    val messageLiveData = MutableLiveData<Int>()
    val retrofitClient:RetrofitClient
    init {
        retrofitClient = RetrofitClient()
    }
    fun saveScore(name:String, password:String, score:Int){
        //Log.d("Mensaje", "Has entrado en savescore")
        thread{
            //Log.d("Mensaje","El valor de name es "+name)
            val call=retrofitClient.getRetrofit().create(QuizService::class.java).saveScore(name,password,score)
            val body: String?=call.execute().body()
            if(body!=null){
                messageLiveData.postValue(body.toInt())
            }else{
                //Log.d("Mensaje", "body es null")
            }

        }
    }
}