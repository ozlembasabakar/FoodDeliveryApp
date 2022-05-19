package com.example.fooddelivery

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class PopularData(
    @DrawableRes val resId: Int,
    val title: String,
    val price: Double,
    val rate: Double,
    val description: String,
    val calorie: Double,
    val scheduleTime: Double,
    val ingredients: List<Int>
): Parcelable
