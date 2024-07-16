package com.example.constatazione_amichevole.data


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface MacchinaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertMacchina(macchina: Macchina)

    @Delete
    suspend fun deleteMacchina(macchina: Macchina)

    @Query(value = "SELECT * FROM macchine WHERE id = :id")
    suspend fun getMacchinaById(id: Int): Macchina?

    @Update
    suspend fun updateMacchina(macchina: Macchina)

    @Query(value = "SELECT * FROM macchine")
    fun getAllMacchina(): List<Macchina>


}