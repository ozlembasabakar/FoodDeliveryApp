package com.example.fooddelivery

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    val productRepository: ProductRepository,
) : ViewModel() {

    private val selectedProduct = MutableStateFlow<Product?>(null)

    fun onProductSelected(product: Product) {
        selectedProduct.value = product
        Log.d("Ozlemwashere", "$product")
    }

    fun getSelectedProduct() : Product? {
        return selectedProduct.value
    }

    fun addProductToBag() {
        viewModelScope.launch {
            try {
                val result = productRepository.postProducts()
                Log.d("Ozlemwashere", result.toString())
            } catch (exception: Exception) {
                Log.d("Ozlemwashere", exception.message.toString())
            }
        }
    }

}