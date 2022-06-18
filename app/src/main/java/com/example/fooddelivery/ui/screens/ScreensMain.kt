package com.example.fooddelivery.ui.screens

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fooddelivery.data.database.CustomerViewModel
import com.example.fooddelivery.data.database.CustomerViewModelFactory
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object Destinations {
    const val Home = "Home"
    const val Detail = "Detail"
    const val Login = "Login"
    const val SignUp = "SignUp"
    const val ForgotPassword = "ForgotPassword"

    object DetailArgs {
        const val foodData = "foodData"
    }
}

@Composable
fun ScreensMain(customerViewModel: CustomerViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destinations.Login,
        builder = {

            composable(Destinations.Login) {
                LoginScreen(navController = navController, Firebase.auth)
            }

            composable(Destinations.SignUp) {
                SignUpScreen(navController = navController, customerViewModel = customerViewModel)
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
        }
    )

}