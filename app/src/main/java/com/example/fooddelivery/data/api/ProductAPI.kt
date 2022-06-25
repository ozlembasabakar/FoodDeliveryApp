package com.example.fooddelivery.data.api

import com.example.fooddelivery.Product
import com.google.gson.annotations.SerializedName
import com.squareup.okhttp.RequestBody
import com.squareup.okhttp.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ProductAPI {

    @GET(APIConstants.END_POINT_GET)
    suspend fun getProduct(): List<Product>

    @POST(APIConstants.END_POINT_POST)
    @FormUrlEncoded
    suspend fun postProduct(
        @Field("user") user: String,
        @Field("title") title: String,
        @Field("price") price: Double,
        @Field("description") description: String,
        @Field("category") category: String,
        @Field("image") image: String,
        @Field("rate") rate: Double,
        @Field("count") count: Int,
        @Field("sale_state") saleState: Int
    ): Response<Responseoglu>
}

class Responseoglu(
    val status: String,
    val message: String
)