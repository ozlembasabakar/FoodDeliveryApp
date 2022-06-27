package com.example.fooddelivery.cart

import com.example.fooddelivery.product.Product
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

const val END_POINT_CART = "get_bag_products_by_user.php"
const val END_POINT_DELETE_PRODUCT = "delete_from_bag.php"


interface CartApi {

    @POST(END_POINT_CART)
    @FormUrlEncoded
    suspend fun getBagProductsByUser(
        @Field("user") user: String,
    ): List<Product>

    @POST(END_POINT_DELETE_PRODUCT)
    @FormUrlEncoded
    suspend fun deleteFromBag(
        @Field("id") id: Int,
    ): Response<Unit>

}