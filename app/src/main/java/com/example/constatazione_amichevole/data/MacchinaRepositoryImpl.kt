package com.example.constatazione_amichevole.data

import kotlinx.coroutines.flow.Flow

class MacchinaRepositoryImpl(private val macchinaDao: MacchinaDao): MacchinaRepository {



    override suspend fun insertMacchina(macchina: Macchina) {
        macchinaDao.insertMacchina(macchina)
    }

    override suspend fun deleteMacchina(macchina: Macchina) {
        macchinaDao.deleteMacchina(macchina)
    }

    override suspend fun getMacchinaById(id: Int): Macchina? {
        return macchinaDao.getMacchinaById(id)
    }

    override fun getAllMacchina(): Flow<List<Macchina>> {
        return macchinaDao.getAllMacchina()
    }

}