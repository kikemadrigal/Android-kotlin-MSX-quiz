package es.tipolisto.msxquiz.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.tipolisto.msxquiz.model.Image

import es.tipolisto.msxquiz.model.Quiz
import es.tipolisto.msxquiz.model.provaiders.QuizProvider
import es.tipolisto.msxquiz.model.network.QuizService
import es.tipolisto.msxquiz.model.network.RetrofitClient
import java.io.IOException
import java.lang.Exception
import kotlin.concurrent.thread
import kotlin.random.Random

/*
** Coded by kikemadrigal
** https://github.com/kikemadrigal
*/


class QuizViewModel : ViewModel() {
    val quizListProviderLiveData = MutableLiveData<List<Quiz>>()
    val quizProviderLiveData = MutableLiveData<Quiz>()
    val quizListInternetLiveData=MutableLiveData<List<Quiz>>()
    val imageInternetLiveData=MutableLiveData<Image>()
    val retrofitClient:RetrofitClient



    init {
        retrofitClient = RetrofitClient()
    }
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
        thread{
            //val call=retrofitClient.getRetrofit().create(QuizService::class.java).getQuizList("getQuizModelList")
            try{
                val call=retrofitClient.getRetrofit().create(QuizService::class.java).getQuizList()
                val body: List<Quiz>?=call.execute().body()
                if(body!=null){
                    quizListInternetLiveData.postValue(body!!)
                    QuizProvider.setQuizList(body)
                }
            }catch (ex:IOException){
                quizListInternetLiveData.postValue(emptyList())
            }

        }
    }
    fun getImageInternet(image: Int) {
        thread {
            //Log.d("Mensaje", "Vamos a ver ña imagen "+image.toString())
            try{
                val call = retrofitClient.getRetrofit().create(QuizService::class.java).getImage("getImage/" + image.toString())
                val body: Image? = call.execute().body()
                if (body != null) {
                    imageInternetLiveData.postValue(body!!)
                }
            }catch (ex:Exception){ }

        }
    }


    fun getList():MutableList<Quiz>{
        return QuizProvider.getQuizList()
    }

}