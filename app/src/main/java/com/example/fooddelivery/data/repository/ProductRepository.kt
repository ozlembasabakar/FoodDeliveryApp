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

    suspend fun postProducts(product: Product): Response<Responseoglu> {
        with(product) {
            return productAPI.postProduct(
                user = "OzlemB",
                title = title,
                price = price,
                description = description,
                category = category,
                image = image,
                rate = rate,
                count = 1,
                saleState = saleState
            )
        }
    }
}


