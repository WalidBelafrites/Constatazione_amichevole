package com.example.constatazione_amichevole.data



interface MacchinaRepository {

    suspend fun insertMacchina(macchina: Macchina)

    suspend fun deleteMacchina(macchina: Macchina)

    suspend fun getMacchinaById(id: Int): Macchina?

    suspend fun updateMacchina(macchina: Macchina)

    suspend fun getAllMacchina(): List<Macchina>

}