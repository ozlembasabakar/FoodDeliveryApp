package com.example.fooddelivery.signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ContactViewModel : ViewModel() {

    private val _viewState = MutableLiveData<SignUpViewState>(SignUpViewState())
    val viewState: LiveData<SignUpViewState> = _viewState

    fun register(
        onUserRegistered: () -> Unit,
        onError: () -> Unit,
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
        onError: () -> Unit,
    ) {
        val contact = hashMapOf(
            "Name" to viewState.value!!.name.text.value,
            "Surname" to viewState.value!!.surname.text.value,
            "Email" to viewState.value!!.email.text.value,
            "Phone Number" to viewState.value!!.phoneNumber.text.value,
            "Password" to viewState.value!!.password.text.value
        )

        val db = Firebase.firestore
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