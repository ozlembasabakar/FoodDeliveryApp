package com.example.fooddelivery.cart

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.product.Product
import com.example.fooddelivery.customer.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val customerRepository: CustomerRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(CartViewState(emptyList<Product>()))
    val state: StateFlow<CartViewState>
        get() = _state

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            val products = cartRepository.getProducts(customerRepository.getCustomerFromRoom())
            _state.value = _state.value.copy(product = products)
        }
    }

    fun deleteProducts(id: Int) {
        viewModelScope.launch {
            val result = cartRepository.deleteProducts(id)
            fetchProducts()
        }
    }
}