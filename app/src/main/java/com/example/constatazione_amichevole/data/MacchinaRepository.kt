package com.example.constatazione_amichevole.data

import kotlinx.coroutines.flow.Flow

interface MacchinaRepository {

    suspend fun insertMacchina(macchina: Macchina)

    suspend fun deleteMacchina(macchina: Macchina)

    suspend fun getMacchinaById(id: Int): Macchina?

    fun getAllMacchina(): Flow<List<Macchina>>

}