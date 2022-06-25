package com.example.fooddelivery.favorite

import android.annotation.SuppressLint
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fooddelivery.Product
import com.example.fooddelivery.R
import com.example.fooddelivery.cart.CartViewModel
import com.example.fooddelivery.product.detail.BoxWithRes
import com.example.fooddelivery.ui.theme.BlackTextColor
import com.example.fooddelivery.ui.theme.Typography
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

    Column {
        AddToFavoriteHeader(navController = navController)

        Spacer(modifier = Modifier.height(32.dp))

        LazyColumn {
            items(state) { product: FavoriteItem ->

                Column(
                    modifier = Modifier
                        .padding(start = 12.dp, end = 12.dp),
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
                        }
                        Spacer(modifier = Modifier.weight(1f))

                        //Spacer(modifier = Modifier.weight(1f))
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

