package com.example.fooddelivery.data

import com.example.fooddelivery.MainActivity

data class UserProfile(
    val email: String,
    val password: String
) {
    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }
}
