package com.example.fooddelivery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fooddelivery.cart.AddToCard
import com.example.fooddelivery.data.database.CustomerItem
import com.example.fooddelivery.data.database.CustomerViewModel
import com.example.fooddelivery.home.HomeScreen
import com.example.fooddelivery.product.detail.ProductDetailScreen
import com.example.fooddelivery.ui.screens.*
import com.example.fooddelivery.viewmodel.LoginViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object Destinations {
    const val Home = "Home"
    const val Detail = "Detail"
    const val Login = "Login"
    const val SignUp = "SignUp"
    const val ForgotPassword = "ForgotPassword"
    const val ProductList = "ProductList"
    const val AddCart = "AddCart"

}

@Composable
fun FoodDeliveryNavHost(customerViewModel: CustomerViewModel) {

    val navigationViewModel: NavigationViewModel = hiltViewModel()


    val loginViewModel: LoginViewModel = viewModel()
    val viewStateLogin by loginViewModel.viewStateLogin.observeAsState()

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destinations.Login,
        builder = {

            composable(Destinations.Login) {
                LoginScreen(navController = navController, Firebase.auth)
            }

            composable(Destinations.SignUp) {
                SignUpScreen(navController = navController)
            }

            composable(Destinations.ForgotPassword) {
                ForgotPasswordScreen(navController = navController)
            }

            composable(Destinations.Home) {
                HomeScreen(
                    navController = navController,
                    onProductSelected = { product ->
                        navigationViewModel.onProductSelected(product)
                    }
                )
            }

            composable(Destinations.Detail) {
                ProductDetailScreen(
                    navController = navController,
                    getSelectedProduct = {
                        navigationViewModel.getSelectedProduct()
                    }
                )
            }


            composable(Destinations.AddCart) {
                AddToCard(
                    navController = navController,
                    getSelectedProduct = {
                        navigationViewModel.getSelectedProduct()
                    }
                )
            }
        }
    )

}