package com.example.constatazione_amichevole.data

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

class ProfiloViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProfiloRepositoryImpl
    private val _profilo = MutableLiveData<List<Profilo>>()
    val profilo: LiveData<List<Profilo>> get() = _profilo


    init {
        val profiloDao = UserDatabase.getDatabase(application).profiloDao
        repository = ProfiloRepositoryImpl(profiloDao)
        loadAllProfilo()
    }

    fun insert(profilo: Profilo) = viewModelScope.launch {
        withContext(Dispatchers.IO){ repository.insertProfilo(profilo) }
        loadAllProfilo()
    }

    fun delete(profilo: Profilo) = viewModelScope.launch {
        withContext(Dispatchers.IO){ repository.deleteProfilo(profilo) }
        loadAllProfilo()
    }

    fun update(profilo: Profilo) = viewModelScope.launch {
        withContext(Dispatchers.IO){ repository.updateProfilo(profilo) }
        loadAllProfilo()
    }


    private fun loadAllProfilo() = viewModelScope.launch {
        withContext(Dispatchers.IO){ _profilo.postValue(repository.getAllProfilo())}
    }
}
