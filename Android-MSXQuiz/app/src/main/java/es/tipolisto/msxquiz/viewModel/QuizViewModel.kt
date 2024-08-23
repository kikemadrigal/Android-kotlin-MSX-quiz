package es.tipolisto.msxquiz.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.tipolisto.msxquiz.model.Image

import es.tipolisto.msxquiz.model.Quiz
import es.tipolisto.msxquiz.model.provaiders.QuizProvider
import es.tipolisto.msxquiz.model.network.QuizService
import es.tipolisto.msxquiz.model.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import kotlin.concurrent.thread
import kotlin.random.Random

/*
** Coded by kikemadrigal
** https://github.com/kikemadrigal
*/


class QuizViewModel : ViewModel() {
    //val quizListProviderLiveData = MutableLiveData<List<Quiz>>()
    //Contiene el quiz que se va a mostrar
    val quizProviderLiveData = MutableLiveData<Quiz>()
    val quizListInternetLiveData=MutableLiveData<List<Quiz>>()
    val imageInternetLiveData=MutableLiveData<Image>()

    fun getQuiz(){
        //Obtenemos la lista que hemos metido en el splahScreen que obtuvimos por internet
        var quizList=QuizProvider.getQuizList()
        if (quizList.size>0){
            val randomIndex = Random.nextInt(quizList.size);
            val quiz=quizList[randomIndex]
            quizList.removeAt(randomIndex)
            QuizProvider.setQuizList(quizList)
            quizProviderLiveData.postValue(quiz)
        }else{
            quizProviderLiveData.postValue(Quiz(0,"empty","","","",0,0,""))
        }
    }
    fun getQuidListInternet(){
        //Log.d("Mensaje", "Vamos a pedir la lista")
        val api=RetrofitClient.getRetrofitApi()
        CoroutineScope(Dispatchers.IO).launch {
           try{
                val response=api.getQuizList()
                val list=response.body()
                if(list!=null){
                    quizListInternetLiveData.postValue(list!!)
                    QuizProvider.setQuizList(list)
                }else{
                    quizListInternetLiveData.postValue(emptyList())
                    //Log.d("Mensaje", "La lista es null")
                }
            }catch (ex:IOException){
                //Log.d("Mensaje", "Error de conexión "+ex.message)
                quizListInternetLiveData.postValue(emptyList())
            }

        }
    }
    fun getImageInternet(image: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("Mensaje", "Vamos a ver ña imagen "+image.toString())
            try{
                val response = RetrofitClient.getRetrofitApi().getImage(image.toString())
                //val body: Image? = call.execute().body()
                val body: Image? = response.body()
                if (body != null) {
                    imageInternetLiveData.postValue(body!!)
                    Log.d("Mensaje", "Imagen recibida"+body!!.toString())
                }else
                    Log.d("Mensaje", "body de la imagen es null")
            }catch (ex:Exception){
                Log.d("Mensaje", "Error de conexión "+ex.message)
            }
        }
    }


    fun getList():MutableList<Quiz>{
        return QuizProvider.getQuizList()
    }

}