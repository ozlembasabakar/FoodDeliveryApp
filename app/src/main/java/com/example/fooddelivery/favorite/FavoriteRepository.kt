package com.example.fooddelivery.favorite

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val favoriteDao: FavoriteDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    fun insertFavorite(addFavorite: FavoriteItem) {
        coroutineScope.launch(Dispatchers.IO) {
            //favoriteDao.deleteTable()
            favoriteDao.insertFavorite(addFavorite)
        }
    }

    suspend fun getFavorite(): List<FavoriteItem> {
        return favoriteDao.getAllFavorites()
    }

    suspend fun deleteFavorites() {
        favoriteDao.deleteFavorites()
    }
}