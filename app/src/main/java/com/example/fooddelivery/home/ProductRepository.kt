package com.example.fooddelivery.home

import com.example.fooddelivery.Product
import com.example.fooddelivery.data.api.ProductAPI
import com.example.fooddelivery.data.api.Responseoglu
import com.example.fooddelivery.data.customer.CustomerDao
import com.google.firebase.auth.FirebaseUser
import retrofit2.Response
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productAPI: ProductAPI, val customerDao: CustomerDao) {
    suspend fun getProducts(): List<Product> {
        return productAPI.getProduct()
    }

    suspend fun postProducts(product: Product, quantity: Int): Response<Responseoglu> {
        with(product) {
            return productAPI.postProduct(
                user = customerDao.getCustomerEmail().first(),
                title = title,
                price = price,
                description = description,
                category = category,
                image = image,
                rate = rate,
                count = quantity,
                saleState = saleState
            )
        }
    }
}


