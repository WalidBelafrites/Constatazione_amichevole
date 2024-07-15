package com.example.constatazione_amichevole.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/*@Dao
interface ConstatazioneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertConstatazione(constatazione: Constatazione)

    @Delete
    suspend fun deleteConstatazione(constatazione: Constatazione)

    @Query(value = "SELECT * FROM constatazione WHERE id = :id")
    suspend fun getConstatazioneById(id: Int): Constatazione?

    @Query(value = "SELECT * FROM constatazione")
    fun getAllConstatazione(): List<Constatazione>
}*/

@Dao
interface ConstatazioneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConstatazione(constatazione: Constatazione)

    @Delete
    suspend fun deleteConstatazione(constatazione: Constatazione)

    @Query("SELECT * FROM constatazione WHERE id = :id")
    suspend fun getConstatazioneById(id: Int): Constatazione?

    @Query("SELECT * FROM constatazione")
    fun getAllConstatazione(): List<Constatazione>
}
