package com.example.fooddelivery.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class CustomerItem(

    @ColumnInfo(name = "name")
    val name:String?,

    @ColumnInfo(name = "surname")
    val surname: String?,

    @PrimaryKey
    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "password")
    val password: String?,

    @ColumnInfo(name = "phoneNumber")
    val phoneNumber: String?,
)
