package com.example.fooddelivery.product.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.Product
import com.example.fooddelivery.home.ProductRepository
import com.example.fooddelivery.favorite.FavoriteItem
import com.example.fooddelivery.favorite.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    val favoriteRepository: FavoriteRepository
    ) : ViewModel() {

    fun addProductToBag(product: Product, quantity: Int) {
        viewModelScope.launch {
            try {
                val result = productRepository.postProducts(product = product, quantity = quantity)
                Log.d("Ozlemwasheres", result.toString())
            } catch (exception: Exception) {
                Log.d("Ozlemwasheree", exception.message.toString())
            }
        }
    }

    fun saveToDb(product: Product) {
        val favoriteItem = FavoriteItem(
            title = product.title,
            count = product.count,
            description = product.description,
            id = product.id,
            image = product.image,
            price = product.price,
            rate = product.rate,
            saleState = product.saleState,
            user = product.image
        )

        favoriteRepository.insertFavorite(favoriteItem)
    }
}
