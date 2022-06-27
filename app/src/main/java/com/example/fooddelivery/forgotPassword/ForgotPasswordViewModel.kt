package com.example.fooddelivery.forgotPassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fooddelivery.forgotPassword.ForgotPasswordViewState

class ForgotPasswordViewModel : ViewModel() {
    private val _viewStateForgotPassword = MutableLiveData<ForgotPasswordViewState>(
        ForgotPasswordViewState())
    val viewStateForgotPassword: LiveData<ForgotPasswordViewState> = _viewStateForgotPassword
}
