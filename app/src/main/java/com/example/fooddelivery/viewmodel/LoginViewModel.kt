package com.example.fooddelivery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fooddelivery.view.LoginViewState

class LoginViewModel: ViewModel() {
    private val _viewStateLogin = MutableLiveData<LoginViewState>(LoginViewState())
    val viewStateLogin: LiveData<LoginViewState> = _viewStateLogin

    fun togglePasswordVisibilityLogin() {
        val currentVisibility = _viewStateLogin.value!!.isPasswordVisible
        _viewStateLogin.value = viewStateLogin.value!!.copy(isPasswordVisible = currentVisibility.not())
    }

    fun login(
        onUserLoggedIn: () -> Unit,
        onError: () -> Unit
    ) {
        if (viewStateLogin.value!!.isValidEmail && viewStateLogin.value!!.isPasswordValid) {
            onUserLoggedIn
        } else {
            onError()
        }
    }


}