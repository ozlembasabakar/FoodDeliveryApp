package com.example.fooddelivery.home

import androidx.annotation.DrawableRes

data class CategoryData(
    @DrawableRes val resId: Int,
    val category: String,
    val title: String
)
