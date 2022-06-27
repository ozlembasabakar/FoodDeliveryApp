package com.example.fooddelivery

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fooddelivery.cart.AddToCard
import com.example.fooddelivery.data.customer.CustomerViewModel
import com.example.fooddelivery.favorite.AddToFavorite
import com.example.fooddelivery.home.HomeScreen
import com.example.fooddelivery.product.detail.ProductDetailScreen
import com.example.fooddelivery.ui.screens.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object Destinations {
    const val Home = "Home"
    const val Detail = "Detail"
    const val Login = "Login"
    const val SignUp = "SignUp"
    const val ForgotPassword = "ForgotPassword"
    const val CheckoutScreen = "CheckoutScreen"
    const val AddCart = "AddCart"
    const val AddFavorite = "AddFavorite"

}

@Composable
fun FoodDeliveryNavHost(customerViewModel: CustomerViewModel) {

    val navigationViewModel: NavigationViewModel = hiltViewModel()

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destinations.Home,
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

            composable(Destinations.AddFavorite) {
                AddToFavorite(
                    navController = navController,
                    getSelectedProduct = {
                        navigationViewModel.getSelectedProduct()
                    }
                )
            }

            composable(Destinations.CheckoutScreen){
                CheckoutScreen(navController = navController)
            }
        }
    )

}