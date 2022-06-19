package com.example.fooddelivery.data

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fooddelivery.MainActivity
import com.example.fooddelivery.ui.screens.Destinations
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class ContactViewModel : ViewModel() {

    private val _viewState = MutableLiveData<SignUpViewState>(SignUpViewState())
    val viewState: LiveData<SignUpViewState> = _viewState

    fun register(
        onUserRegistered: () -> Unit,
        onError: () -> Unit
    ) {
        if (viewState.value!!.isValidData) {
            addUserToFirebase(onUserRegistered, onError)
        } else {
            _viewState.value = viewState.value!!.copy(showValidationError = true)
            onError()
        }
    }

    private fun addUserToFirebase(
        onUserRegistered: () -> Unit,
        onError: () -> Unit
    ) {
        val db = Firebase.firestore
        val contact = hashMapOf(
            "Name" to viewState.value!!.name.text.value,
            "Surname" to viewState.value!!.surname.text.value,
            "Email" to viewState.value!!.email.text.value,
            "Phone Number" to viewState.value!!.phoneNumber.text.value,
            "Password" to viewState.value!!.password.text.value
        )

        db.collection("contacts").add(contact)
            .addOnSuccessListener { documentReference ->
                onUserRegistered()
            }
            .addOnFailureListener { e ->
                onError()
            }
    }

    fun togglePasswordVisibility() {
        val currentVisibility = _viewState.value!!.isPasswordVisible
        _viewState.value = viewState.value!!.copy(isPasswordVisible = currentVisibility.not())
    }
}