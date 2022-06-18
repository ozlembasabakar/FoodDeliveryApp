package com.example.fooddelivery.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.*

@Dao
interface CustomerDao {

    @Query("SELECT * FROM customers")
    fun getAllCustomers(): LiveData<List<CustomerItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer: CustomerItem)
}