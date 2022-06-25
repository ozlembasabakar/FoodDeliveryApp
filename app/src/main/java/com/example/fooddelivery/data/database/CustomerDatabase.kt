package com.example.fooddelivery.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CustomerItem::class], version = 1)
abstract class CustomerDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao
}