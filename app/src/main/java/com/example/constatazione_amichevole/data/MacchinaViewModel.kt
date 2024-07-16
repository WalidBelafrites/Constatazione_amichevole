import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.constatazione_amichevole.data.Macchina
import com.example.constatazione_amichevole.data.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MacchinaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MacchinaRepositoryImpl
    private val _macchine = MutableLiveData<List<Macchina>>()
    val macchine: LiveData<List<Macchina>> get() = _macchine

    init {
        val macchinaDao = UserDatabase.getDatabase(application).macchinaDao
        repository = MacchinaRepositoryImpl(macchinaDao)
        loadAllMacchine()
    }

    fun insert(macchina: Macchina) = viewModelScope.launch {
        withContext(Dispatchers.IO){ repository.insertMacchina(macchina) }
        loadAllMacchine()
    }

    fun delete(macchina: Macchina) = viewModelScope.launch {
        withContext(Dispatchers.IO){ repository.deleteMacchina(macchina) }
        loadAllMacchine()
    }

    fun update(macchina: Macchina) = viewModelScope.launch {
        withContext(Dispatchers.IO){ repository.updateMacchina(macchina) }
        loadAllMacchine()
    }

    private fun loadAllMacchine() = viewModelScope.launch {
        withContext(Dispatchers.IO){ _macchine.postValue(repository.getAllMacchina())}
    }
}
