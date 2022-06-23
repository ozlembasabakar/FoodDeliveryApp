package com.example.fooddelivery.data.api

import com.example.fooddelivery.Product
import retrofit2.http.GET

interface ProductAPI {

    @GET(APIConstants.END_POINT)
    suspend fun getProduct(): List<Product>

}