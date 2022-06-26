package com.example.fooddelivery.home

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import com.example.fooddelivery.Product

data class HomeScreenViewState(
    val products: List<Product>,
    val selectedCategory: String = ""
) {
    val listByCategory by derivedStateOf {
        if(selectedCategory != "") {
            products.filter {
                it.category == selectedCategory
            }
        } else {
            products
        }
    }
}