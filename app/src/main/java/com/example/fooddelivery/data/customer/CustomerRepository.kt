package com.example.fooddelivery.data.customer

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CustomerRepository @Inject constructor(private val customerDao: CustomerDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    fun insertCustomer(newCustomer: CustomerItem) {
        coroutineScope.launch(Dispatchers.IO) {
            customerDao.deleteTable()
            customerDao.insertCustomer(newCustomer)
        }
    }

    suspend fun getCustomer(): List<CustomerItem> {
        return customerDao.getAllCustomers()
    }
}