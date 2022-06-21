package com.example.fooddelivery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fooddelivery.view.ForgotPasswordViewState
import com.example.fooddelivery.view.LoginViewState

class ForgotPasswordViewModel : ViewModel() {
    private val _viewStateForgotPassword = MutableLiveData<ForgotPasswordViewState>(
        ForgotPasswordViewState())
    val viewStateForgotPassword: LiveData<ForgotPasswordViewState> = _viewStateForgotPassword
}
