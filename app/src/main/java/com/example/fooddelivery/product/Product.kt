package com.example.fooddelivery.product


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val category: String,
    val count: Int,
    val description: String,
    val id: String,
    val image: String,
    val price: Double,
    val rate: Double,
    val saleState: Int,
    val title: String,
    val user: String,
) : Parcelable