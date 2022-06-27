package com.example.fooddelivery.home

import com.example.fooddelivery.product.Product
import com.example.fooddelivery.product.ProductAPI
import com.example.fooddelivery.product.Responseoglu
import com.example.fooddelivery.customer.CustomerDao
import retrofit2.Response
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productAPI: ProductAPI,
    val customerDao: CustomerDao,
) {
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

    suspend fun getProductsByUser(): List<Product> {
        return productAPI.getProductsByUser(
            user = "ozlembasabakar"
        )
    }
}



