package com.example.constatazione_amichevole.data

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow

interface MacchinaRepository {

    suspend fun insertMacchina(macchina: Macchina)

    suspend fun deleteMacchina(macchina: Macchina)

    suspend fun getMacchinaById(id: Int): Macchina?

    suspend fun getAllMacchina(): List<Macchina>

}