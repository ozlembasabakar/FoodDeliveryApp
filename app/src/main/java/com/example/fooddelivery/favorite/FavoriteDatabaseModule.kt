package com.example.fooddelivery.favorite

import com.example.fooddelivery.data.customer.CustomerDatabase
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

/*    @Provides
    @Singleton
    fun provideFavoriteDatabase(@ApplicationContext appContext: Context): FavoriteDatabase {
        return Room.databaseBuilder(
            appContext,
            FavoriteDatabase::class.java,
            "FoodDelivery"
        ).build()
    }*/
}