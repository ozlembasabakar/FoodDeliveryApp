package com.example.fooddelivery.di

import com.example.fooddelivery.data.api.APIConstants
import com.example.fooddelivery.data.api.ProductAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ProductAPIModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder{
        return Retrofit.Builder().baseUrl(APIConstants.BASE_URL).addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideAPI(builder: Retrofit.Builder): ProductAPI{
        return builder.build().create(ProductAPI::class.java)
    }
}



