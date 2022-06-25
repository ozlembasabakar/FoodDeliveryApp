package com.example.fooddelivery

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fooddelivery.data.customer.CustomerViewModel
import com.example.fooddelivery.data.customer.CustomerViewModelFactory
import com.example.fooddelivery.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodDeliveryTheme {
                val customerOwner = LocalViewModelStoreOwner.current

                customerOwner?.let {
                    val customerViewModel: CustomerViewModel = viewModel(
                        it,
                        "CustomerViewModel",
                        CustomerViewModelFactory(
                            LocalContext.current.applicationContext as Application
                        )
                    )

                    FoodDeliveryNavHost(customerViewModel = customerViewModel)
                }
            }
        }
    }
}