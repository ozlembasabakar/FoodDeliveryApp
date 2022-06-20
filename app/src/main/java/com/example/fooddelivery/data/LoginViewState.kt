package com.example.fooddelivery.data

import android.util.Patterns
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

data class LoginViewState(
    val email: TextInputState = TextInputState(),
    val password: TextInputState = TextInputState(),
    val isPasswordVisible: Boolean = false,
) {
    val isValidEmail by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email.text.value).matches()
    }

    val isPasswordValid by derivedStateOf {
        password.text.value.length >= 8
    }

    @Composable
    fun checkVisibility(): Boolean {
        var itemVisibility by rememberSaveable { mutableStateOf(false) }

        return itemVisibility
    }
}