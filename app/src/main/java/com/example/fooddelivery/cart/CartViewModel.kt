package com.example.fooddelivery.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.Product
import com.example.fooddelivery.data.customer.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val customerRepository: CustomerRepository
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<Product>())
    val state: StateFlow<List<Product>>
        get() = _state



    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            val products = cartRepository.getProducts(customerRepository.getCustomerFromRoom())
            _state.value = products
        }
    }

    fun deleteProducts(id: Int) {
        viewModelScope.launch {
            val result = cartRepository.deleteProducts(id)
            fetchProducts()
        }
    }
}