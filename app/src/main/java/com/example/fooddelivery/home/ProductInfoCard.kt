package com.example.fooddelivery.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddelivery.Product
import com.example.fooddelivery.R
import com.example.fooddelivery.ui.theme.*
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun ProductInfoCard(
    modifier: Modifier = Modifier,
    product: Product,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(176.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(176.dp)
                .padding(end = 13.dp)
                .clip(
                    RoundedCornerShape(18.dp)
                )
                //.clickable {
                // = Bundle().apply {
                //     putParcelable(Destinations.DetailArgs.foodData, popularData)
                //   }
                // navController.navigate(Destinations.Detail)
                //}
                .background(CardItemBg)
        )

        Column(
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp)
        ) {
            Box(modifier = Modifier.height(40.dp), contentAlignment = Alignment.Center) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.crown),
                        contentDescription = "Crown",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(11.dp))

                    Text(
                        text = "Best Selling",
                        style = Typography.h1,
                        fontSize = 14.sp,
                        color = TextColor
                    )
                }
            }
            Box(modifier = Modifier.height(40.dp), contentAlignment = Alignment.Center) {
                Text(
                    text = "${product.title}",
                    color = BlackTextColor,
                    style = Typography.body1,
                    fontSize = 18.sp
                )
            }
            Box(modifier = Modifier.height(40.dp), contentAlignment = Alignment.Center) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$",
                        style = Typography.body1,
                        fontSize = 14.sp,
                        color = Orange500
                    )

                    Text(
                        text = "${product.price}",
                        style = Typography.body1,
                        fontSize = 20.sp,
                        color = BlackTextColor
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
        )
        {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(width = 60.dp, height = 40.dp)
                        .clip(
                            RoundedCornerShape(bottomStart = 18.dp, topEnd = 18.dp)
                        )
                        .background(Yellow500),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = "Add Button",
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(48.dp))

                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "Rating Star",
                        modifier = Modifier.size(16.dp),
                        tint = BlackTextColor
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "${product.rate}",
                        style = Typography.body1,
                        color = BlackTextColor
                    )
                }
            }
        }
        //val imagePainter = rememberAsyncImagePainter(product.image)

        GlideImage(
            imageModel = product.image,
            // Crop, Fit, Inside, FillHeight, FillWidth, None
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .size(120.dp)
                .align(
                    Alignment.CenterEnd
                )
        )
/*
        Image(
            painter = painterResource(id = product.image),
            contentDescription = "",
            modifier = Modifier
                .size(156.dp)
                .align(
                    Alignment.CenterEnd
                )
        )
*/
    }

    Spacer(modifier = Modifier.height(20.dp))
}
