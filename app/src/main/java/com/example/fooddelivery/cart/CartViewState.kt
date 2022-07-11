package com.example.fooddelivery.cart

import com.example.fooddelivery.product.Product

data class CartViewState(
    val product: List<Product>,
) {

    fun totalAmount(): Double {
        return product.sumOf {
            it.price * it.count
        }
    }
}