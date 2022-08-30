package es.tipolisto.msxquiz.model.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import es.tipolisto.msxquiz.model.provaiders.RecordProvider
import es.tipolisto.msxquiz.util.Constants
import kotlin.concurrent.thread

class RecordDatabaseClient(context: Context)  {
    companion object{
        lateinit var recordsDB : RecordDatabase
        //lateinit var recordList: List<RecordEntity>
    }
    init{
        //Log.d(Constants.TAG,"Pasa por el contructor de la base de datos")
        recordsDB = Room.databaseBuilder(
            context,
            RecordDatabase::class.java, "database"
        ).build()
    }

}