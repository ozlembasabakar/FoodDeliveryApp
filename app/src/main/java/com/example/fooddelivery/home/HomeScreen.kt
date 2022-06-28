package com.example.fooddelivery.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
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
import com.example.fooddelivery.product.Product
import com.example.fooddelivery.R
import com.example.fooddelivery.product.detail.BoxWithRes
import com.example.fooddelivery.ui.theme.*
import com.example.fooddelivery.product.ProductViewModel

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
                        CategoryData(resId = R.drawable.all, category = "", title = "All"),
                        CategoryData(resId = R.drawable.crown_outlined,
                            category = "Popular",
                            title = "Popular"),
                        CategoryData(resId = R.drawable.pizza, category = "Pizza", title = "Pizza"),
                        CategoryData(resId = R.drawable.hamburger,
                            category = "Burgers",
                            title = "Burger"),
                        CategoryData(resId = R.drawable.drinks,
                            category = "Drinks",
                            title = "Drink"),
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
                    modifier = Modifier
                        .clip(RoundedCornerShape(18.dp))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication =
                            rememberRipple(bounded = true),
                            onClick = {
                                onProductSelected(product)
                                navController.navigate(Destinations.Detail)
                            }
                        ),
                    product = product
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Header(navController: NavController) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 13.dp)
    ) {
        BoxWithRes(resId = R.drawable.bag, description = "Menu", modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                navController.navigate(Destinations.AddCart)
            }
        )

        BoxWithRes(resId = R.drawable.favorite_border,
            description = "Favorite",
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication =
                rememberRipple(bounded = true),
                onClick = {
                    navController.navigate(Destinations.AddFavorite)
                }
            )
        )
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
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication =
                        rememberRipple(bounded = true),
                        onClick = {
                            onCategoryItemSelected(category.category)
                        }
                    ),
                categoryData = category,
                isSelected = category.category == selectedCategory
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
                contentDescription = categoryData.category,
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