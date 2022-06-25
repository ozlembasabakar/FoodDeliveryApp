package com.example.fooddelivery.product.detail

import android.util.Log
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
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
) : ViewModel() {

    fun addProductToBag(product: Product) {
        viewModelScope.launch {
            try {
                val result = productRepository.postProducts(product)
                Log.d("Ozlemwasheres", result.toString())
            } catch (exception: Exception) {
                Log.d("Ozlemwasheree", exception.message.toString())
            }
        }
    }
}
