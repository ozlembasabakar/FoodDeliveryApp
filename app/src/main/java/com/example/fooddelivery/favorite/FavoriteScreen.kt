package com.example.fooddelivery.favorite

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fooddelivery.Destinations
import com.example.fooddelivery.Product
import com.example.fooddelivery.R
import com.example.fooddelivery.cart.CartViewModel
import com.example.fooddelivery.product.detail.BoxWithRes
import com.example.fooddelivery.ui.theme.*
import com.skydoves.landscapist.glide.GlideImage


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AddToFavorite(
    navController: NavController,
    getSelectedProduct: () -> Product?,
) {

    val favoriteViewModel: FavoriteViewModel = hiltViewModel()
    val state by favoriteViewModel.state.collectAsState()

    val product = getSelectedProduct()

    Column(modifier = Modifier.padding(start = 30.dp, top = 40.dp, end = 17.dp)
    ) {
        AddToFavoriteHeader(navController = navController)

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(state) { product: FavoriteItem ->

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
                                .fillMaxSize()
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "€",
                                    fontSize = 12.sp,
                                    color = Orange500,
                                    modifier = Modifier
                                        .padding(bottom = 4.dp, end = 4.dp)
                                )

                                Text(
                                    text = product.price.toString(),
                                    fontSize = 14.sp,
                                    color = BlackTextColor,
                                    modifier = Modifier
                                        .padding(bottom = 4.dp)
                                )
                            }

                            Spacer(modifier = Modifier.weight(1f))

                            Box(contentAlignment = Alignment.Center) {
                                Row(modifier = Modifier.padding(end = 8.dp),
                                    horizontalArrangement = Arrangement.Center) {
                                    Icon(painter = painterResource(id = R.drawable.star),
                                        contentDescription = "Rating Star",
                                        modifier = Modifier.size(10.dp),
                                        tint = BlackTextColor)

                                    Text(
                                        text = product.rate.toString(),
                                        fontSize = 14.sp,
                                        color = BlackTextColor,
                                        modifier = Modifier
                                            .padding(bottom = 4.dp)
                                    )
                                }

                            }

                        }
                    }

                }

            }
        }
    }
}

@Composable
fun AddToFavoriteHeader(navController: NavController) {
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

