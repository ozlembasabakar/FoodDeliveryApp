package com.example.fooddelivery.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fooddelivery.Destinations
import com.example.fooddelivery.product.Product
import com.example.fooddelivery.R
import com.example.fooddelivery.product.detail.BoxWithRes
import com.example.fooddelivery.ui.theme.*
import com.skydoves.landscapist.glide.GlideImage
import kotlin.math.absoluteValue


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AddToCard(
    navController: NavController,
) {

    val cartViewModel: CartViewModel = hiltViewModel()
    val state by cartViewModel.state.collectAsState()

    Column(
        modifier = Modifier.padding(start = 30.dp, top = 40.dp, end = 17.dp)
    ) {
        AddToCartHeader(navController = navController)

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .weight(1f)) {
            items(state.product) { product: Product ->

                Row(
                    modifier = Modifier
                        .padding(start = 4.dp, top = 4.dp, bottom = 4.dp, end = 4.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    GlideImage(
                        imageModel = product.image,
                        // Crop, Fit, Inside, FillHeight, FillWidth, None
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(4.dp)
                            .size(70.dp)
                            .clip(RoundedCornerShape(10.dp))

                    )

                    Spacer(modifier = Modifier.size(12.dp))

                    Column {
                        Text(
                            text = product.title,
                            color = BlackTextColor,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                                .width(180.dp)
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "€",
                                fontSize = 12.sp,
                                color = Orange500,
                                modifier = Modifier
                                    .padding(bottom = 4.dp, end = 4.dp)
                            )

                            Text(
                                text = if (product.count > 1) "${product.price} x ${product.count}" else "${product.price}",
                                fontSize = 14.sp,
                                color = BlackTextColor,
                                modifier = Modifier
                                    .padding(bottom = 4.dp)
                            )
                        }

                        Spacer(modifier = Modifier.size(12.dp))
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication =
                                rememberRipple(bounded = true),
                                onClick = {
                                    cartViewModel.deleteProducts(product.id.toInt())
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.remove),
                            contentDescription = "", modifier = Modifier
                                .size(26.dp)
                                .padding(4.dp)

                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(text = "Total Amount:  ${state.totalAmount()}€")

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .size(width = 133.dp, height = 40.dp)
                    .clip(RoundedCornerShape(topEnd = 18.dp, topStart = 18.dp))
                    .background(
                        Yellow500
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication =
                        rememberRipple(bounded = true),
                        onClick = {
                            navController.navigate(Destinations.CheckoutScreen)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Checkout", style = Typography.body1, color = Color.White)
            }
        }
    }
}


@Composable
fun AddToCartHeader(navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        BoxWithRes(
            resId = R.drawable.arrow_left,
            description = "Back",
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication =
                    rememberRipple(bounded = true),
                    onClick = {
                        navController.navigateUp()
                    }
                )
        )
    }
}

