package com.example.constatazione_amichevole.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface ProfiloDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfilo(profilo: Profilo)

    @Delete
    suspend fun deleteProfilo(profilo: Profilo)

    @Query("SELECT * FROM profilo WHERE id = :id")
    suspend fun getProfiloById(id: Int): Profilo?

    @Update
    suspend fun updateProfilo(profilo: Profilo)

    @Query("SELECT * FROM profilo")
    fun getAllProfilo(): List<Profilo>
}
