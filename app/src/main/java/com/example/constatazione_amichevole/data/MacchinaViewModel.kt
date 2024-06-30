import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.constatazione_amichevole.data.Macchina
import com.example.constatazione_amichevole.data.MacchinaRepositoryImpl
import com.example.constatazione_amichevole.data.UserDatabase
import kotlinx.coroutines.launch

class MacchinaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MacchinaRepositoryImpl

    init {
        val macchinaDao = UserDatabase.getDatabase(application).macchinaDao
        repository = MacchinaRepositoryImpl(macchinaDao)
    }

    fun insert(macchina : Macchina) = viewModelScope.launch {
        repository.insertMacchina(macchina)
    }

    fun delete(macchina : Macchina) = viewModelScope.launch {
        repository.deleteMacchina(macchina)
    }

    fun getMacchinaById(id: Int) = viewModelScope.launch {
        repository.getMacchinaById(id)
    }

    fun getAllProducts() = viewModelScope.launch {
        repository.getAllMacchina()
    }
}
