package es.tipolisto.msxquiz.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class RecordEntity (
    //Identificador único dentro de la base de datos
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "date") val date: String? = null,
    @ColumnInfo(name = "score") val score:Int = 0

)