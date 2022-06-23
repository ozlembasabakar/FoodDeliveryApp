package com.example.fooddelivery.ui.screens

import android.telecom.Call
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fooddelivery.AssetParamType
import com.example.fooddelivery.data.PopularData
import com.example.fooddelivery.data.database.CustomerViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object Destinations {
    const val Home = "Home"
    const val Detail = "Detail"
    const val Login = "Login"
    const val SignUp = "SignUp"
    const val ForgotPassword = "ForgotPassword"
    const val ProductList = "ProductList"

    object DetailArgs {
        const val foodData = "foodData"
    }
}

@Composable
fun ScreensMain(customerViewModel: CustomerViewModel) {
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
                HomeScreen(navController = navController)
            }

            composable(Destinations.Detail) {
                DetailScreen(navController = navController)
            }

            composable(Destinations.ProductList) {
                ProductList()
            }



        }
    )

}