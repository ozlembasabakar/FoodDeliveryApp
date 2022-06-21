package com.example.fooddelivery.view

import android.util.Patterns
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue

data class ForgotPasswordViewState(
    val email: TextInputState = TextInputState(),
) {
    val isValidEmail by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email.text.value).matches() and email.text.value.isNotBlank()
    }

    val isInvalidEmail by derivedStateOf {
        email.text.value.isBlank()
    }
}