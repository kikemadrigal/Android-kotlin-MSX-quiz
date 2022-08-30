package es.tipolisto.msxquiz.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.tipolisto.msxquiz.model.database.RecordDatabaseClient
import es.tipolisto.msxquiz.model.database.RecordEntity
import es.tipolisto.msxquiz.model.provaiders.RecordProvider
import es.tipolisto.msxquiz.view.activities.RecyclerAdapter
import kotlin.concurrent.thread

class RecordViewModel: ViewModel()  {
    private lateinit var recyclerView : RecyclerView
    private val recyclerAdapter: RecyclerAdapter = RecyclerAdapter()
    val listRescordsMutableLiveData=MutableLiveData<List<RecordEntity>>()
    val maxPointsLiveData=MutableLiveData<Int>()


    fun addScore(name : String, points : Int){
        thread {
            RecordDatabaseClient.recordsDB.recordDao().insert(RecordEntity(0, name,"0",points))
        }
    }

    fun updateRanking(){
        thread {
            //Obtenemos los 10 records últimos de la base de datos
            var listRecords=RecordDatabaseClient.recordsDB.recordDao().getLast10RecordEntities()
            //Obtenemos la lista estática
            val staticList=RecordProvider.getStaticListRecords()
            listRecords.addAll(staticList)
            listRecords.sortByDescending {
                it.score
            }
            //Log.d(Constants.TAG,"Actualizados records: ${listRecords.size.toString()}")
            RecordProvider.setRecordList(listRecords)
        }
    }
    fun getmaxPoints(){
        thread {
            val maxPoints = RecordDatabaseClient.recordsDB.recordDao().getMaxRecordEntities()
            maxPointsLiveData.postValue(maxPoints)
        }
    }
    fun getAll10Records(){
        thread{
            //Nuestra lista ya estaba almacenada en un provider
            listRescordsMutableLiveData.postValue(RecordProvider.getRecordList())
            //Log.d(Constants.TAG,"Obtenidos dentro el view model records ${RecordProvider.getRecordList().size.toString()}")
        }
    }

    fun setRecyclerView(context : Context, recyclerView: RecyclerView){
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        //recyclerAdapter.RecyclerAdapter(RecordDatabaseClient.recordList, context)
        recyclerAdapter.RecyclerAdapter(RecordProvider.getRecordList(), context)
        recyclerView.adapter = recyclerAdapter
    }

    fun checkEmptyList(){
        //Log.d(Constants.TAG, "Lista vacia, insertamos 10 records")
        thread {
            val listRecords=RecordDatabaseClient.recordsDB.recordDao().getLast10RecordEntities()
            if(listRecords.isEmpty()){

                RecordDatabaseClient.recordsDB.recordDao()
                    .insert(RecordEntity(0, "Trinity", "1/1/2021", 10))
                RecordDatabaseClient.recordsDB.recordDao()
                    .insert(RecordEntity(1, "Neo", "1/1/2021", 20))
                RecordDatabaseClient.recordsDB.recordDao()
                    .insert(RecordEntity(2, "Morfeo", "1/1/2021", 34))
                RecordDatabaseClient.recordsDB.recordDao()
                    .insert(RecordEntity(3, "Merovingio", "1/1/2021", 30))

                } else{
                    //Log.d(Constants.TAG, "Lista mno vacia")
                }
            updateRanking()
        }
    }

}