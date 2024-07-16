package com.example.constatazione_amichevole.data

interface ProfiloRepository {
    suspend fun insertProfilo(profilo: Profilo)

    suspend fun deleteProfilo(profilo: Profilo)

    suspend fun getProfiloById(id: Int): Profilo?

    suspend fun updateProfilo(profilo: Profilo)

    suspend fun getAllProfilo(): List<Profilo>
}