package com.example.fooddelivery.product

import retrofit2.Response
import retrofit2.http.*

const val END_POINT_GET = "get_products.php"
const val END_POINT_POST = "add_to_bag.php"
const val END_POINT_GET_PRODUCT_BY_USER = "get_products_by_user.php"

interface ProductAPI {

    @GET(END_POINT_GET)
    suspend fun getProduct(): List<Product>

    @POST(END_POINT_POST)
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
        @Field("sale_state") saleState: Int,
    ): Response<Responseoglu>

    @POST(END_POINT_GET_PRODUCT_BY_USER)
    @FormUrlEncoded
    suspend fun getProductsByUser(
        @Field("user") user: String,
    ): List<Product>

}

class Responseoglu(
    val status: String,
    val message: String,
)
