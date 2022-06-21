package com.example.fooddelivery.view

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
        val passwordRegex = "(?=.*\\d)(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^!/.&+=]).{8,}".toRegex()
        passwordRegex.matches(password.text.value)
    }

    @Composable
    fun checkVisibility(): Boolean {
        var itemVisibility by rememberSaveable { mutableStateOf(false) }

        return itemVisibility
    }
}