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

class ConstatazioneViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ConstatazioneRepositoryImpl
    private val _constatazione = MutableLiveData<List<Constatazione>>()
    val constatazione: LiveData<List<Constatazione>> get() = _constatazione


    init {
        val constatazioneDao = UserDatabase.getDatabase(application).constatazioneDao
        repository = ConstatazioneRepositoryImpl(constatazioneDao)
        loadAllConstatazione()
    }

    fun insert(constatazione: Constatazione) = viewModelScope.launch {
        withContext(Dispatchers.IO){ repository.insertConstatazione(constatazione) }
        loadAllConstatazione()
    }

    fun delete(constatazione: Constatazione) = viewModelScope.launch {
        withContext(Dispatchers.IO){ repository.deleteConstatazione(constatazione) }
        loadAllConstatazione()
    }

    fun update(constatazione: Constatazione) = viewModelScope.launch {
        withContext(Dispatchers.IO){ repository.updateConstatazione(constatazione) }
        loadAllConstatazione()
    }


    private fun loadAllConstatazione() = viewModelScope.launch {
        withContext(Dispatchers.IO){ _constatazione.postValue(repository.getAllConstatazione())}
    }
}
