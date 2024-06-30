package com.example.constatazione_amichevole.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface MacchinaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertMacchina(macchina: Macchina)

    @Delete
    suspend fun deleteMacchina(macchina: Macchina)

    @Query(value = "SELECT * FROM macchina WHERE id = :id")
    suspend fun getMacchinaById(id: Int): Macchina?

    @Query(value = "SELECT * FROM macchina")
    fun getAllMacchina(): Flow<List<Macchina>>
}