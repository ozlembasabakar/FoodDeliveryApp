package com.example.fooddelivery.cart

import com.example.fooddelivery.product.Product
import retrofit2.Response
import javax.inject.Inject

class CartRepository @Inject constructor(private val cartApi: CartApi) {
    suspend fun getProducts(user: String): List<Product> {
        return cartApi.getBagProductsByUser(user)
    }

    suspend fun deleteProducts(id: Int): Response<Unit> {
        return cartApi.deleteFromBag(id)
    }
}