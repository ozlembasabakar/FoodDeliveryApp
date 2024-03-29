package com.example.fooddelivery.product.detail

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fooddelivery.R
import com.example.fooddelivery.product.Product
import com.example.fooddelivery.ui.theme.*
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProductDetailScreen(
    navController: NavController,
    getSelectedProduct: () -> Product?,
) {

    val productDetailViewModel: ProductDetailViewModel = hiltViewModel()

    val count = remember { mutableStateOf(1) }
    val context = LocalContext.current

    val product = getSelectedProduct()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 30.dp, top = 40.dp, end = 30.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        if (product != null) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {
                DetailHeader(
                    navController = navController,
                    productDetailViewModel = productDetailViewModel,
                    product = product)

                Spacer(modifier = Modifier.height(32.dp))

                GlideImage(
                    imageModel = product.image,
                    // Crop, Fit, Inside, FillHeight, FillWidth, None
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .size(200.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = product.title,
                    color = BlackTextColor,
                    style = Typography.body1,
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.size(20.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    Text(
                        text = "€ ",
                        style = Typography.body1,
                        fontSize = 14.sp,
                        color = Orange500
                    )

                    Text(
                        text = product.price.toString(),
                        style = Typography.body1,
                        fontSize = 20.sp,
                        color = BlackTextColor
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    BoxWithResCalc(
                        resId = R.drawable.minus,
                        description = "Minus",
                        iconColor = BlackTextColor,
                        boxSize = 36,
                        iconSize = 14
                    ) {
                        if (count.value >= 2) count.value--
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Text(
                        text = "${count.value}",
                        style = Typography.body2,
                        color = TextColor,
                        fontSize = 18.sp,
                    )

                    Spacer(modifier = Modifier.width(14.dp))

                    BoxWithResCalc(
                        resId = R.drawable.add,
                        description = "Add",
                        boxSize = 36,
                        iconSize = 24,
                        iconColor = Color.White,
                        bgColor = Yellow500
                    ) {
                        if (count.value <= 9) count.value++ else Toast.makeText(context,
                            "You can order up to 10",
                            Toast.LENGTH_LONG).show()
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = product.description,
                    style = Typography.h5,
                    color = TextColor,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .size(width = 203.dp, height = 56.dp)
                        .clip(RoundedCornerShape(topEnd = 18.dp, topStart = 18.dp))
                        .background(
                            Yellow500
                        )
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication =
                            rememberRipple(bounded = true),
                            onClick = {
                                productDetailViewModel.addProductToBag(product,
                                    quantity = count.value)
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Add to card", style = Typography.body1, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun DetailHeader(
    navController: NavController,
    productDetailViewModel: ProductDetailViewModel, product: Product,
) {

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

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(CardItemBg)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication =
                    rememberRipple(bounded = true),
                    onClick = {
                        productDetailViewModel.saveToDb(
                            product = product)
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.favorite_border),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp),
                tint = IconColor
            )
        }
    }
}

@Composable
fun BoxWithResCalc(
    resId: Int,
    description: String,
    bgColor: Color? = CardItemBg,
    iconColor: Color? = IconColor,
    boxSize: Int? = 40,
    iconSize: Int? = 24,
    onClick: () -> Unit,
) {

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = bgColor!!),
        contentPadding = PaddingValues(
            start = 8.dp,
            top = 8.dp,
            end = 8.dp,
            bottom = 8.dp
        ),
        modifier = Modifier.size(boxSize!!.dp)
    ) {
        Icon(
            painter = painterResource(id = resId),
            contentDescription = description,
            modifier = Modifier.size(iconSize!!.dp),
            tint = iconColor!!
        )
    }
}

@Composable
fun BoxWithRes(
    modifier: Modifier = Modifier,
    resId: Int,
    description: String,
    bgColor: Color? = CardItemBg,
    iconColor: Color? = IconColor,
    boxSize: Int? = 40,
    iconSize: Int? = 24,
) {
    Box(
        modifier = modifier
            .size(boxSize!!.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(bgColor!!),
        contentAlignment = Alignment.Center
    )
    {
        Icon(
            painter = painterResource(id = resId),
            contentDescription = description,
            modifier = Modifier.size(iconSize!!.dp),
            tint = iconColor!!
        )
    }
}

