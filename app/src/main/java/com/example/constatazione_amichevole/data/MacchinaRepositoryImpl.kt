import com.example.constatazione_amichevole.data.Macchina
import com.example.constatazione_amichevole.data.MacchinaDao

class MacchinaRepositoryImpl(private val macchinaDao: MacchinaDao) {

    suspend fun insertMacchina(macchina: Macchina) {
        macchinaDao.insertMacchina(macchina)
    }

    suspend fun deleteMacchina(macchina: Macchina) {
        macchinaDao.deleteMacchina(macchina)
    }


    suspend fun getAllMacchina(): List<Macchina> {
        return macchinaDao.getAllMacchina()
    }

    suspend fun getMacchinaById(id: Int): Macchina? {
        return macchinaDao.getMacchinaById(id)
    }
}