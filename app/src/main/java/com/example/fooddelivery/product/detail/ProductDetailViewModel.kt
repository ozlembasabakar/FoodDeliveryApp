package com.example.fooddelivery.product.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.Product
import com.example.fooddelivery.data.customer.CustomerItem
import com.example.fooddelivery.data.repository.ProductRepository
import com.example.fooddelivery.favorite.FavoriteItem
import com.example.fooddelivery.favorite.FavoriteRepository
import com.google.firebase.firestore.QueryDocumentSnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    val favoriteRepository: FavoriteRepository
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
