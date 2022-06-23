package com.example.fooddelivery.data.repository

import com.example.fooddelivery.Product
import com.example.fooddelivery.data.api.ProductAPI
import javax.inject.Inject


class ProductRepository @Inject constructor(private val productAPI: ProductAPI) {
    suspend fun getProducts(): List<Product> {
        return productAPI.getProduct()
    }
}


