package com.example.fooddelivery.favorite

import com.example.fooddelivery.customer.CustomerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class FavoriteDatabaseModule {

    @Provides
    fun provideFavoriteDao(favoriteDatabase: CustomerDatabase): FavoriteDao {
        return favoriteDatabase.favoriteDao()
    }
}