package com.example.fooddelivery.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.Product
import com.example.fooddelivery.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    val productRepository: ProductRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<Product>())
    val state: StateFlow<List<Product>>
        get() = _state

    init {
        viewModelScope.launch {
            val products = productRepository.getProducts()
            _state.value = products
        }
    }
}
