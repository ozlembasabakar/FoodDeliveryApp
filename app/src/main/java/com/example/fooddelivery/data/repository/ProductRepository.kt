package com.example.fooddelivery.data.repository

import com.example.fooddelivery.Product
import com.example.fooddelivery.data.api.ProductAPI
import com.example.fooddelivery.data.api.Responseoglu
import com.google.firebase.auth.FirebaseUser
import retrofit2.Response
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productAPI: ProductAPI) {
    suspend fun getProducts(): List<Product> {
        return productAPI.getProduct()
    }

    suspend fun postProducts(): Response<Responseoglu> {
        return productAPI.postProduct(
            user = "OzlemB",
            title = "aab",
            price = 79.90,
            description = "bb",
            category = "cc",
            image = "dd",
            rate = 4.5,
            count = 100,
            saleState = 0
        )
    }
}


