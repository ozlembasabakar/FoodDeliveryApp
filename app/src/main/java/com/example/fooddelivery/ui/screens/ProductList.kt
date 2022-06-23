package com.example.fooddelivery.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.fooddelivery.Product
import com.example.fooddelivery.R
import com.example.fooddelivery.data.CategoryData
import com.example.fooddelivery.ui.theme.BlackTextColor
import com.example.fooddelivery.ui.theme.Typography
import com.example.fooddelivery.viewmodel.ProductViewModel

@Composable
fun ProductList() {
    val productViewModel: ProductViewModel = hiltViewModel()
    val state by productViewModel.state.collectAsState()
    val navController = rememberNavController()

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
              ProductImageCard(product = product)
            }
        }
    }



@Composable
fun ProductImageCard(product: Product) {


    Card(shape = MaterialTheme.shapes.medium, modifier = Modifier.padding(16.dp)) {
        Box {
            //Image(painter = imagePainter,
            //    contentDescription = "",
            //    modifier = Modifier
            //        .fillMaxWidth()
            //        .height(100.dp),
            //    contentScale = ContentScale.FillBounds)

            Surface(
                color = MaterialTheme.colors.onSurface.copy(alpha = .3f),
                modifier = Modifier.align(Alignment.BottomCenter),
                contentColor = MaterialTheme.colors.surface
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)) {
                    Text(text = "Product title: ${product.title}")
                    Text(text = "Product description: ${product.description}")
                    Text(text = "Product id: ${product.id}")
                    Text(text = "Product price: ${product.price}")
                    Text(text = "Product rate: ${product.rate}")
                    Text(text = "Product category: ${product.category}")
                    Text(text = "Product count: ${product.count}")
                    Text(text = "Product saleState: ${product.saleState}")
                    Text(text = "Product user: ${product.user}")
                }
            }
        }
    }

}