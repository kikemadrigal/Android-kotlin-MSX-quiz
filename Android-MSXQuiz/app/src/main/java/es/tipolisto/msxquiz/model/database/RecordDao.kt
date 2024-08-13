package es.tipolisto.msxquiz.model.database

import androidx.room.*

@Dao
interface RecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recordEntity: RecordEntity): Long

    @Query("SELECT * FROM RecordEntity WHERE id=(:userEntityId)")
    fun getIdRecord(userEntityId: Int): RecordEntity?

    @Query("SELECT * FROM RecordEntity WHERE name LIKE (:userEntityName)")
    fun getNameRecord(userEntityName: String?): RecordEntity?

    @Query("SELECT * FROM RecordEntity")
    fun getAllRecordEntities(): MutableList<RecordEntity>

    //Obtener los últimos 10 registros ordenados por record de mayor a menor
    @Query("SELECT * FROM RecordEntity ORDER  BY score DESC LIMIT 10")
    fun getLast10RecordEntities(): MutableList<RecordEntity>

    //Obtener la maxima puntación
    @Query("SELECT max(score) FROM RecordEntity")
    fun getMaxRecordEntities(): Int

    @Query("SELECT * FROM RecordEntity WHERE name IN (:name)")
    fun loadAllByIds(name: IntArray?): List<RecordEntity?>?


    @Insert
    fun insertAll(vararg users: RecordEntity)

    @Update
    fun update(user: RecordEntity)


    @Delete
    fun delete(user: RecordEntity)

    //Borramos todos los que sean menores de una puntuación
    @Query("DELETE FROM RecordEntity WHERE SCORE<(:score)")
    fun deleteAllMinor(score: Int): Int
}