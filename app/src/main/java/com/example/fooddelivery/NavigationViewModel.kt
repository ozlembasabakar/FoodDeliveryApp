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
class NavigationViewModel @Inject constructor() : ViewModel() {

    private val selectedProduct = MutableStateFlow<Product?>(null)

    fun onProductSelected(product: Product) {
        selectedProduct.value = product
        Log.d("Ozlemwashere", "$product")
    }

    fun getSelectedProduct() : Product? {
        return selectedProduct.value
    }
}