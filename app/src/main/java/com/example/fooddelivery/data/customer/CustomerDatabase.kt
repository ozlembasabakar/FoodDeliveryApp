package com.example.fooddelivery.data.customer

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fooddelivery.favorite.FavoriteDao
import com.example.fooddelivery.favorite.FavoriteItem

@Database(entities = [CustomerItem::class, FavoriteItem::class], version = 1)
abstract class CustomerDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao

    abstract fun favoriteDao(): FavoriteDao

}