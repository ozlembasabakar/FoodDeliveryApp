package com.example.fooddelivery.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Customer(
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val password: String = ""
)