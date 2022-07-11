package com.example.fooddelivery.login

import android.util.Patterns
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.fooddelivery.TextInputState

data class LoginViewState(
    val email: TextInputState = TextInputState(),
    val password: TextInputState = TextInputState(),
    val isPasswordVisible: Boolean = false,
) {
    val isValidEmail by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email.text.value).matches()
    }

    val isValidPassport by derivedStateOf {
        password.text.value == password.text.value
    }

    val isPasswordValid by derivedStateOf {
        val passwordRegex =
            "(?=.*\\d)(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^!/.&+=]).{8,}".toRegex()
        passwordRegex.matches(password.text.value)
    }
}