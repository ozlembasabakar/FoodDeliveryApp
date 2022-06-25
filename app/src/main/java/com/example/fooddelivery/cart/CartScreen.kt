package com.example.fooddelivery.cart

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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
    val context = LocalContext.current
    val count = remember { mutableStateOf(0) }

    val data = getSelectedProduct()

    LazyColumn {
        items(5) {
            Column(
                modifier = Modifier
                    .padding(start = 30.dp, top = 40.dp, end = 30.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                if (data != null) {
                    //TODO: show only design screen
                    AddToCartHeader(navController = navController)

                    Spacer(modifier = Modifier.height(32.dp))

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
                            imageModel = data.image,
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
                                text = data.title,
                                color = BlackTextColor,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(bottom = 4.dp)
                                    .size(width = 20.dp, height = 14.dp)
                            )

                            Text(
                                text = data.price,
                                fontSize = 14.sp,
                                color = BlackTextColor,
                                style = Typography.h1,
                                modifier = Modifier.size(width = 14.dp, height = 12.dp)
                            )

                            Spacer(modifier = Modifier.size(12.dp))

                            Row {
                                BoxWithResCalc(
                                    resId = R.drawable.minus,
                                    description = "Minus",
                                    iconColor = BlackTextColor,
                                    boxSize = 20
                                ) {
                                    if (count.value >= 1) count.value--
                                }

                                Spacer(modifier = Modifier.width(14.dp))

                                Text(
                                    text = "${count.value}",
                                    style = Typography.body2,
                                    color = TextColor,
                                    fontSize = 14.sp,
                                )

                                Spacer(modifier = Modifier.width(14.dp))

                                BoxWithResCalc(
                                    resId = R.drawable.add,
                                    description = "Add",
                                    boxSize = 20,
                                    iconColor = Color.White,
                                    bgColor = Yellow500
                                ) {
                                    if (count.value <= 9) count.value++ else Toast.makeText(context,
                                        "You can order up to 10",
                                        Toast.LENGTH_LONG).show()

                                }
                            }
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Column {
                            Text(
                                text = "total amount",
                                fontSize = 14.sp,
                                color = BlackTextColor
                            )
                            Text(
                                text = if (count.value > 0) "data.price * ${count.value}" else "",
                                fontSize = 10.sp,
                                color = BlackTextColor
                            )
                        }

                        //Spacer(modifier = Modifier.weight(1f))

                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clickable {
                                    count.value = 0
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.remove),
                                contentDescription = "",
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
            navController = navController
        )
    }
}

