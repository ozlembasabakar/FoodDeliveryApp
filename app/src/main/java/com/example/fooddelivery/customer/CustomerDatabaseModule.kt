package com.example.fooddelivery.customer

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CustomerDatabaseModule {
    @Provides
    fun provideCustomerDao(customerDatabase: CustomerDatabase): CustomerDao {
        return customerDatabase.customerDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): CustomerDatabase {
        return Room.databaseBuilder(
            appContext,
            CustomerDatabase::class.java,
            "FoodDelivery"
        ).build()
    }
}