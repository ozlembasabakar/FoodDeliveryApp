package com.example.fooddelivery.data.database

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomerRepository(private val customerDao: CustomerDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertCustomer(newCustomer: CustomerItem) {
        coroutineScope.launch(Dispatchers.IO) {
            customerDao.insertCustomer(newCustomer)
        }
    }
}