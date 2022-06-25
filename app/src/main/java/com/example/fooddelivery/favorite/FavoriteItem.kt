package com.example.fooddelivery.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteItem(

    @ColumnInfo(name = "count")
    val count: Int,

    @ColumnInfo(name = "description")
    val description: String,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "price")
    val price: Double,

    @ColumnInfo(name = "rate")
    val rate: Double,

    @ColumnInfo(name = "saleState")
    val saleState: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "user")
    val user: String,
)