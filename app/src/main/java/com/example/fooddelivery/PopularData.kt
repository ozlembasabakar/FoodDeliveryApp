package com.example.fooddelivery

import androidx.annotation.DrawableRes

data class PopularData(
    @DrawableRes val resId: Int,
    val title: String,
    val price: Double,
    val rate: Double,
    val description: String,
    val calorie: Double,
    val scheduleTime: Double,
    val ingredients: List<Int>
)
