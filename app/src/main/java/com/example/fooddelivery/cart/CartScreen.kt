package com.example.fooddelivery.cart

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
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
import com.example.fooddelivery.Product
import com.example.fooddelivery.R
import com.example.fooddelivery.product.detail.BoxWithRes
import com.example.fooddelivery.product.detail.BoxWithResCalc
import com.example.fooddelivery.ui.theme.BlackTextColor
import com.example.fooddelivery.ui.theme.TextColor
import com.example.fooddelivery.ui.theme.Typography
import com.example.fooddelivery.ui.theme.Yellow500
import com.skydoves.landscapist.glide.GlideImage


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AddToCard(
    navController: NavController,
    getSelectedProduct: () -> Product?,
) {

    val cartViewModel: CartViewModel = hiltViewModel()
    val state by cartViewModel.state.collectAsState()

    val context = LocalContext.current

    Column {
        AddToCartHeader(navController = navController)

        Spacer(modifier = Modifier.height(32.dp))

        LazyColumn {
            items(state) { product: Product ->

                Column(
                    modifier = Modifier
                        .padding(start = 30.dp, top = 40.dp, end = 30.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    //TODO: show only design screen


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
                                .size(70.dp)
                                .clip(RoundedCornerShape(10.dp))
                        )

/*
            Image(
                painter = painterResource(id = R.drawable.man),
                contentDescription = "",
                modifier = Modifier.size(70.dp))
*/

                        Spacer(modifier = Modifier.size(12.dp))

                        Column {
                            Text(
                                text = product.title,
                                color = BlackTextColor,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(bottom = 4.dp)
                                    .size(width = 20.dp, height = 14.dp)
                            )

                            Text(
                                text = product.price.toString(),
                                fontSize = 14.sp,
                                color = BlackTextColor,
                                style = Typography.h1,
                                modifier = Modifier.size(width = 14.dp, height = 12.dp)
                            )

                            Spacer(modifier = Modifier.size(12.dp))
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = if (product.count > 1) "${product.price} * ${product.count}" else "${product.price}",
                            fontSize = 10.sp,
                            color = BlackTextColor
                        )

                        //Spacer(modifier = Modifier.weight(1f))

                        Box(
                            modifier = Modifier
                                .size(36.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.remove),
                                contentDescription = "", modifier = Modifier.clickable {
                                    cartViewModel.deleteProducts(product.id.toInt())
                                }
                            )
                        }
                    }
                }
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
            modifier = Modifier.clickable {
                navController.navigateUp()
            }
        )
    }
}

