package com.example.fooddelivery

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

const val BASE_URL = "https://canerture.com/api/ecommerce/"

interface APIService {
    @GET("get_products.php")
    suspend fun getProducts(): List<Product>

    companion object {
        var apiService: APIService? = null
        fun getInstance(): APIService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }

    fun call() = apiService!!.addToBag("user","title","price","description", "category", "image", "rate", "count", "sale_state")

    @POST("get_products_by_user.php")
    @FormUrlEncoded
    suspend fun getProductsByUser(
        @Field("user") user: String,
    ): List<Product>

    @POST("add_to_bag.php")
    @FormUrlEncoded
    fun addToBag(
        @Field("user") user: String,
        @Field("title") title: String,
        @Field("price") price: String,
        @Field("description") description: String,
        @Field("category") category: String,
        @Field("image") image: String,
        @Field("rate") rate: String,
        @Field("count ") count: String,
        @Field("sale_state") sale_state: String,
    ): Call<ApiCallResponse> //CRUDResponse

}