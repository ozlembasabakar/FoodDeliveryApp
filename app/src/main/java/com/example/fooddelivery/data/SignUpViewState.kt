package com.example.fooddelivery.data

import android.util.Patterns
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

data class SignUpViewState(
    val name: TextInputState = TextInputState(),
    val surname: TextInputState = TextInputState(),
    val email: TextInputState = TextInputState(),
    val phoneNumber: TextInputState = TextInputState(),
    val password: TextInputState = TextInputState(),
    val confirmedPassword: TextInputState = TextInputState(),
    val isPasswordVisible: Boolean = false,
    val showValidationError: Boolean = false
) {

    val isValidName by derivedStateOf {
        name.text.value.isNotBlank()
    }
    val isValidSurname by derivedStateOf {
        surname.text.value.isNotBlank()
    }
    val isValidEmail by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email.text.value).matches()
    }
    val isValidPhoneNumber by derivedStateOf {
        Patterns.PHONE.matcher(phoneNumber.text.value).matches()
    }

    val passwordRegex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^!/&+=]).{8,}".toRegex()
    val isValidPassword by derivedStateOf {
        passwordRegex.matches(password.text.value)
    }
    val isValidConfirmedPassword by derivedStateOf {
        password.text.value == confirmedPassword.text.value
    }

    val isValidData by derivedStateOf {
        isValidName && isValidSurname && isValidEmail && isValidPhoneNumber && isValidPassword && isValidConfirmedPassword
    }

    @Composable
    fun checkVisibility(): Boolean {
        var itemVisibility by rememberSaveable { mutableStateOf(false) }

        return itemVisibility
    }
}