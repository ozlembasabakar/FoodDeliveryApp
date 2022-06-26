package com.example.fooddelivery.viewmodel

import android.telephony.data.RouteSelectionDescriptor
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.Product
import com.example.fooddelivery.home.HomeScreenViewState
import com.example.fooddelivery.home.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository,
) : ViewModel() {

    fun onCategoryItemSelected(selectedCategory: String) {
        // selectedCategoryyi viewstateteki selectedCategorye atıcak
        _state.value = state.value.copy(selectedCategory = selectedCategory)
    }

    private val _state = MutableStateFlow(HomeScreenViewState(emptyList()))
    val state: StateFlow<HomeScreenViewState>
        get() = _state

    init {
        viewModelScope.launch {
            val products = productRepository.getProductsByUser()
            _state.value = state.value.copy(products = products)
        }
    }
}