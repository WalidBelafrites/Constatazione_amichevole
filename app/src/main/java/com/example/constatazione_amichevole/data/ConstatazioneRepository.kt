package com.example.constatazione_amichevole.data

interface ConstatazioneRepository {
    suspend fun insertConstatazione(constatazione: Constatazione)

    suspend fun deleteConstatazione(constatazione: Constatazione)

    suspend fun getConstatazioneById(id: Int): Constatazione?

    suspend fun updateConstatazione(constatazione: Constatazione)

    suspend fun getAllConstatazione(): List<Constatazione>
}