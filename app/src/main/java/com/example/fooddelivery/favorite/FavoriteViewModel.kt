package com.example.fooddelivery.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.Product
import com.example.fooddelivery.cart.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<FavoriteItem>())
    val state: StateFlow<List<FavoriteItem>>
        get() = _state

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            val products = favoriteRepository.getFavorite()
            _state.value = products
        }
    }
}