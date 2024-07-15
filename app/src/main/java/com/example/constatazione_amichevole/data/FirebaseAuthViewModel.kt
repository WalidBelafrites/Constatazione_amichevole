package com.example.constatazione_amichevole.data

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class FirebaseAuthViewModel(application: Application) : AndroidViewModel(application) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val currentUser = MutableLiveData(auth.currentUser)

    fun signUp(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password).await()
                currentUser.postValue(auth.currentUser)
                showToast("Sign Up Successful")
                onSuccess()
            } catch (e: Exception) {
                showToast("Sign Up Failed: ${e.message}")
            }
        }
    }

    fun signIn(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password).await()
                currentUser.postValue(auth.currentUser)
                showToast("Sign In Successful")
                onSuccess()
            } catch (e: Exception) {
                showToast("Sign In Failed: ${e.message}")
            }
        }
    }

    fun signOut() {
        auth.signOut()
        currentUser.value = auth.currentUser
        showToast("Signed Out")
    }

    private fun showToast(message: String) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show()
    }
}
