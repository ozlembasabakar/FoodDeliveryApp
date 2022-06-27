package com.example.fooddelivery.cart

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartApiModule {

    @Provides
    @Singleton
    fun cartAPI(builder: Retrofit.Builder): CartApi {
        return builder.build().create(CartApi::class.java)
    }
}