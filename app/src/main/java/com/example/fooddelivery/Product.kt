package com.example.fooddelivery


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val category: String,
    val count: String,
    val description: String,
    val id: String,
    val image: String,
    val price: String,
    val rate: String,
    val saleState: String,
    val title: String,
    val user: String
): Parcelable