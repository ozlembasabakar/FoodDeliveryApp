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
    val calorie: Int,
    val scheduleTime: Int,
    val ingredients: List<Int>
): Parcelable
