package com.example.fooddelivery

import androidx.compose.runtime.mutableStateOf

class TextInputState(
    initialValue: String = "",
    private val onTextChanged: () -> Unit = {}
) {

    internal var text = mutableStateOf(initialValue)
        private set

    fun onTextChanged(value: String) {
        if(text.value != value) {
            onTextChanged()
        }
        text.value = value
    }
}