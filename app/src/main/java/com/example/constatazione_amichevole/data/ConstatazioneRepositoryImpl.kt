package com.example.constatazione_amichevole.data

class ConstatazioneRepositoryImpl(private val constatazioneDao: ConstatazioneDao) {

    suspend fun insertConstatazione(constatazione: Constatazione) {
        constatazioneDao.insertConstatazione(constatazione)
    }

    suspend fun deleteConstatazione(constatazione: Constatazione) {
        constatazioneDao.deleteConstatazione(constatazione)
    }

    suspend fun updateConstatazione(constatazione: Constatazione) {
        constatazioneDao.updateConstatazione(constatazione)
    }

    fun getAllConstatazione(): List<Constatazione> {
        return constatazioneDao.getAllConstatazione()
    }

    suspend fun getConstatazioneById(id: Int): Constatazione? {
        return constatazioneDao.getConstatazioneById(id)
    }
}