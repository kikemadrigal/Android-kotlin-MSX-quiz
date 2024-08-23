package es.tipolisto.msxquiz.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.tipolisto.msxquiz.model.network.QuizService
import es.tipolisto.msxquiz.model.network.RetrofitClient
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class LoginInternetViewModel:ViewModel() {
    val messageLiveData = MutableLiveData<Int>()
    val seeProgressBarchLiveData = MutableLiveData<Boolean>()
    fun saveScore(name:String, password:String, score:Int){
        //Log.d("Mensaje", "Has entrado en savescore")
        viewModelScope.launch{
            seeProgressBarchLiveData.postValue(true)
            //Log.d("Mensaje","El valor de name es "+name)
            val response=RetrofitClient.getRetrofitApi().saveScore(name,password,score)
            val body: String?=response.body()
            if(body!=null){
                messageLiveData.postValue(body.toInt())
            }else{
                //Log.d("Mensaje", "body es null")
            }
            seeProgressBarchLiveData.postValue(false)
        }
    }
}