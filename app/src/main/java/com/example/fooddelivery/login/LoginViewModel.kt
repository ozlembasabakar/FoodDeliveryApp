package com.example.fooddelivery.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.fooddelivery.Destinations
import com.example.fooddelivery.customer.CustomerItem
import com.example.fooddelivery.customer.CustomerRepository
import com.example.fooddelivery.favorite.FavoriteRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.logging.Handler
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val customerRepository: CustomerRepository,
    val favoriteRepository: FavoriteRepository,
) : ViewModel() {
    private val _viewStateLogin = MutableLiveData<LoginViewState>(LoginViewState())
    val viewStateLogin: LiveData<LoginViewState> = _viewStateLogin


    fun togglePasswordVisibilityLogin() {
        val currentVisibility = _viewStateLogin.value!!.isPasswordVisible
        _viewStateLogin.value =
            viewStateLogin.value!!.copy(isPasswordVisible = currentVisibility.not())
    }

    fun login(
        onUserLoggedIn: () -> Unit,
        onError: () -> Unit,
    ) {
        if (viewStateLogin.value!!.isValidEmail && viewStateLogin.value!!.isPasswordValid) {
            onUserLoggedIn
        } else {
            onError()
        }
    }

    fun saveUserToDb(email: String) {
        val db = Firebase.firestore
        db.collection("contacts").whereEqualTo("Email", email)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    saveToDb(document)
                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents: ", exception)
            }
    }

    private fun saveToDb(document: QueryDocumentSnapshot) {
        val customerItem = CustomerItem(
            name = document["Name"].toString(),
            surname = document["Surname"].toString(),
            email = document["Email"].toString(),
            password = null,
            phoneNumber = document["Phone Number"].toString()
        )

        customerRepository.insertCustomer(customerItem)
    }

    fun resetFavorites() {
        viewModelScope.launch {
            favoriteRepository.deleteFavorites()
        }
    }
}