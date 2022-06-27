package com.example.fooddelivery

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fooddelivery.cart.CartViewModel
import com.example.fooddelivery.ui.theme.Typography
import com.example.fooddelivery.ui.theme.Yellow500

@Composable
fun CheckoutScreen(
    navController: NavController,
) {

    val cartViewModel: CartViewModel = hiltViewModel()
    val state by cartViewModel.state.collectAsState()

    Column(
        modifier = Modifier.background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(60.dp))

        Box(
            modifier = Modifier
                .padding(top = 30.dp)
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.check_circle_outline),
                contentDescription = "",
                modifier = Modifier.size(180.dp))
        }
        Box(
            modifier = Modifier
                .padding(35.dp)
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(
                    Yellow500
                )
                .clickable {
                    for (item in cartViewModel.state.value) {
                        cartViewModel.deleteProducts(item.id.toInt())
                    }
                    navController.navigate(Destinations.Home)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "CONTINUE SHOPPING",
                style = Typography.body1,
                color = Color.White)
        }

        Spacer(modifier = Modifier.size(120.dp))
    }
}
