package com.example.fooddelivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.fooddelivery.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodDeliveryTheme {
                FoodDeliveryNavHost()
            }
        }
    }
}
