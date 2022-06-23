package com.example.fooddelivery

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.fooddelivery.data.CategoryData
import com.example.fooddelivery.ui.screens.CategoryList
import com.example.fooddelivery.ui.screens.Header
import com.example.fooddelivery.ui.screens.OrderNowBox
import com.example.fooddelivery.ui.theme.*
import com.example.fooddelivery.viewmodel.ProductViewModel
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun ProductCard() {

    val productViewModel: ProductViewModel = hiltViewModel()
    val state by productViewModel.state.collectAsState()
    val navController = rememberNavController()

    //val scrollState = rememberScrollState()

    //val imagePainter = rememberAsyncImagePainter(product.image)

    LazyColumn {

        item {
            Header(navController = navController)

            Spacer(modifier = Modifier.height(32.dp))

            OrderNowBox()

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Categories",
                style = Typography.body1,
                fontSize = 22.sp,
                color = BlackTextColor
            )

            Spacer(modifier = Modifier.height(20.dp))

            CategoryList(
                categories = listOf(
                    CategoryData(resId = R.drawable.pizza, title = "Pizza"),
                    CategoryData(resId = R.drawable.hamburger, title = "Burger"),
                    CategoryData(resId = R.drawable.drinks, title = "Drinks"),
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Popular",
                style = Typography.body1,
                fontSize = 22.sp,
                color = BlackTextColor
            )

            Spacer(modifier = Modifier.height(30.dp))
        }
        if (state.isEmpty()) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )

            }
        }
        items(state) { product: Product ->
            ProductInfoCard(product = product)
        }
    }
}

@Composable
fun ProductInfoCard(product: Product) {
    Box(
        modifier = Modifier
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

                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
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

