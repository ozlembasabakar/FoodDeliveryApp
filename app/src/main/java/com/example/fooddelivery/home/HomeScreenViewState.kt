package com.example.fooddelivery.home

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import com.example.fooddelivery.product.Product

data class HomeScreenViewState(
    val products: List<Product>,
    val selectedCategory: String = "",
) {
    val listByCategory by derivedStateOf {
        if (selectedCategory != "") {
            if (selectedCategory == "Popular") {
                products.sortedByDescending {
                    it.rate
                }.take(10)
                // products.take(10)
            } else {
                products.filter {
                    it.category == selectedCategory
                }
            }
        } else {
            products
        }
    }
}
