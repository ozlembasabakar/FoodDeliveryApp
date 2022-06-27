package com.example.fooddelivery.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fooddelivery.Destinations
import com.example.fooddelivery.Product
import com.example.fooddelivery.R
import com.example.fooddelivery.data.CategoryData
import com.example.fooddelivery.data.PopularData
import com.example.fooddelivery.product.detail.BoxWithRes
import com.example.fooddelivery.ui.theme.*
import com.example.fooddelivery.viewmodel.ProductViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    onProductSelected: (Product) -> Unit,
) {
    val productViewModel: ProductViewModel = hiltViewModel()
    val state by productViewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, top = 40.dp, end = 17.dp)
    ) {

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
                        CategoryData(resId = R.drawable.crown_outlined, title = "Popular"),
                        CategoryData(resId = R.drawable.pizza, title = "Pizza"),
                        CategoryData(resId = R.drawable.hamburger, title = "Burger"),
                        CategoryData(resId = R.drawable.drinks, title = "Drinks"),
                    ),
                    selectedCategory = state.selectedCategory,
                    onCategoryItemSelected = productViewModel::onCategoryItemSelected
                )

                Spacer(modifier = Modifier.height(20.dp))

            }
            if (state.listByCategory.isEmpty()) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )

                }
            }
            items(state.listByCategory) { product: Product ->
                ProductInfoCard(
                    modifier = Modifier.clickable {
                        onProductSelected(product)
                        navController.navigate(Destinations.Detail)
                    },
                    product = product
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Header(navController: NavController) {

    var visible by remember {
        mutableStateOf(true)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 13.dp)
    ) {
        BoxWithRes(resId = R.drawable.bag, description = "Menu", modifier = Modifier.clickable {
            navController.navigate(Destinations.AddCart)
        }
        )

        BoxWithRes(resId = R.drawable.favorite_border,
            description = "Favorite",
            modifier = Modifier.clickable {
                navController.navigate(Destinations.AddFavorite)
            })

/*
        Row {
            var columnWidth = 40.dp
            AnimatedVisibility(!visible) {
                Box(modifier = Modifier
                    .width(columnWidth + 180.dp)
                    .height(40.dp)
                    .background(Color.Transparent),
                    contentAlignment = Alignment.CenterStart) {
                    Text(text = "Search",
                        color = Color(0xFFA0A0A0).copy(alpha = 0.6f))
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Box(modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    CardItemBg!!)
                .clickable { visible = !visible },
                contentAlignment = Alignment.Center) {
                Image(painter = painterResource(id = R.drawable.search),
                    contentDescription = "Search", modifier = Modifier.size(24.dp))
            }
        }
*/
        //BoxWithRes(resId = R.drawable.search, description = "Search")

    }
}

@Composable
fun OrderNowBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(156.dp)
            .padding(end = 13.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                Yellow200
            )
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.SpaceBetween) {

                Text(buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = BlackTextColor,
                            fontStyle = Typography.body1.fontStyle
                        )
                    ) {
                        append(
                            "The Fastest In\n" + "Delivery "
                        )
                    }

                    withStyle(
                        style = SpanStyle(
                            color = Yellow500,
                            fontStyle = Typography.body1.fontStyle
                        )
                    ) {
                        append(
                            "Food"
                        )
                    }
                }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.man),
                contentDescription = "Man",
                modifier = Modifier.size(156.dp)
            )

        }
    }
}

@Composable
fun CategoryList(
    categories: List<CategoryData>,
    selectedCategory: String,
    onCategoryItemSelected: (String) -> Unit,
) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 13.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(categories) { category ->
            CategoryItem(
                modifier = Modifier.clickable {
                    onCategoryItemSelected(category.title)
                },
                categoryData = category,
                isSelected = category.title == selectedCategory
            )

            Spacer(modifier = Modifier.width(13.dp))
        }
    }
}

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    categoryData: CategoryData,
    isSelected: Boolean = false,
) {

    Box(
        modifier = modifier
            .size(width = 106.dp, height = 146.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                if (isSelected) Yellow500 else CardItemBg
            ), contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = categoryData.resId),
                contentDescription = categoryData.title,
                modifier = Modifier.size(48.dp),
                tint = if (isSelected) Color.White else BlackTextColor

            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = categoryData.title,
                style = Typography.body2,
                color = if (isSelected) Color.White else BlackTextColor
            )
        }
    }

}

@Composable
fun PopularList(popularList: List<PopularData>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        for (item in popularList) {
            PopularItem(popularData = item, navController = navController)
        }
    }
}

@Composable
fun PopularItem(popularData: PopularData, navController: NavController) {

    Column {
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
                        text = popularData.title,
                        color = BlackTextColor,
                        style = Typography.body1,
                        fontSize = 18.sp
                    )
                }
                Box(modifier = Modifier.height(40.dp), contentAlignment = Alignment.Center) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "€",
                            style = Typography.body1,
                            fontSize = 14.sp,
                            color = Orange500
                        )

                        Text(
                            text = popularData.price.toString(),
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

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "Rating Star",
                            modifier = Modifier.size(16.dp),
                            tint = BlackTextColor
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "${popularData.rate}",
                            style = Typography.body1,
                            color = BlackTextColor
                        )
                    }
                }
            }

            Image(
                painter = painterResource(id = popularData.resId),
                contentDescription = popularData.title,
                modifier = Modifier
                    .size(156.dp)
                    .align(
                        Alignment.CenterEnd
                    )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}