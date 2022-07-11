package com.example.fooddelivery.home

import com.example.fooddelivery.product.APIConstants
import com.example.fooddelivery.product.ProductAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ProductAPIModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        val logging = HttpLoggingInterceptor()

        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)


        return Retrofit.Builder()
            .baseUrl(APIConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
    }

    @Provides
    @Singleton
    fun productAPI(builder: Retrofit.Builder): ProductAPI {
        return builder.build().create(ProductAPI::class.java)
    }
}



