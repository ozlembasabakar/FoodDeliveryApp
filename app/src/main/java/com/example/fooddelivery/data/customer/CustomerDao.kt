package com.example.fooddelivery.data.customer

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {

    @Query("SELECT * FROM customers")
    suspend fun getAllCustomers(): List<CustomerItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer: CustomerItem)

    @Query("DELETE FROM customers")
    suspend fun deleteTable()

    @Query("SELECT email FROM customers")
    suspend fun getCustomerEmail(): List<String>
}