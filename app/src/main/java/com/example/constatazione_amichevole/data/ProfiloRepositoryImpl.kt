package com.example.constatazione_amichevole.data

class ProfiloRepositoryImpl(private val profiloDao: ProfiloDao) {

    suspend fun insertProfilo(profilo: Profilo) {
        profiloDao.insertProfilo(profilo)
    }

    suspend fun deleteProfilo(profilo: Profilo) {
        profiloDao.deleteProfilo(profilo)
    }

    suspend fun updateProfilo(profilo: Profilo) {
        profiloDao.updateProfilo(profilo)
    }

    fun getAllProfilo(): List<Profilo> {
        return profiloDao.getAllProfilo()
    }

    suspend fun getProfiloById(id: Int): Profilo? {
        return profiloDao.getProfiloById(id)
    }
}