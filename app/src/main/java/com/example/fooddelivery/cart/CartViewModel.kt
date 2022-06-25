package com.example.fooddelivery.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fooddelivery.Product
import com.example.fooddelivery.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<Product>())
    val state: StateFlow<List<Product>>
        get() = _state

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            val products = cartRepository.getProducts("OzlemB")
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