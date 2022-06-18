package com.example.fooddelivery.data.database

import android.app.Application
import androidx.lifecycle.ViewModel

class CustomerViewModel(application: Application) : ViewModel() {

    private val customerRepository: CustomerRepository

    init {
        val customerDB = CustomerDatabase.getInstance(application)
        val customerDao = customerDB.customerDao()
        customerRepository = CustomerRepository(customerDao)
    }

    fun insertCustomer(customer: CustomerItem) {
        customerRepository.insertCustomer(customer)
    }
}