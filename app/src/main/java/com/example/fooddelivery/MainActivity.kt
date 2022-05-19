package com.example.fooddelivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fooddelivery.ui.theme.*

object Destinations {
    const val Home = "Home"
    const val Detail = "Detail"

    object DetailArgs {
        const val foodData = "foodData"
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodDeliveryTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Destinations.Home,
                    builder = {

                        composable(Destinations.Home) {
                            HomeScreen(navController = navController)
                        }

                        composable(Destinations.Detail) {
                            DetailScreen(navController = navController)
                        }

                    })

            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, top = 40.dp, end = 17.dp)
    ) {
        Column {

            Header()

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

            PopularList(
                popularList = listOf(
                    PopularData(
                        R.drawable.salad_pesto_pizza,
                        title = "Salad Pesto Salad",
                        description = "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form.",
                        price = 10.55,
                        calorie = 540.0,
                        scheduleTime = 20.0,
                        rate = 5.0,
                        ingredients = listOf(
                            R.drawable.ing1,
                            R.drawable.ing2,
                            R.drawable.ing3,
                            R.drawable.ing4,
                            R.drawable.ing5
                        )
                    ),
                    PopularData(
                        R.drawable.primavera_pizza,
                        title = "Primavera Pizza",
                        description = "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form.",
                        price = 12.55,
                        calorie = 450.0,
                        scheduleTime = 30.0,
                        rate = 4.7,
                        ingredients = listOf(
                            R.drawable.ing1,
                            R.drawable.ing2,
                            R.drawable.ing3,
                            R.drawable.ing4,
                            R.drawable.ing5
                        )
                    )
                ),
                navController = navController
            )
        }
    }
}

@Composable
fun DetailScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 30.dp, top = 40.dp, end = 30.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        val data =
            navController.previousBackStackEntry?.arguments?.getParcelable<PopularData>(Destinations.DetailArgs.foodData)

        if (data != null) {
            Text(text = data.title)
        } else {
            //TODO: show only design screen
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                DetailHeader()

                Spacer(modifier = Modifier.height(32.dp))

                Image(
                    painter = painterResource(id = R.drawable.salad_pesto_pizza),
                    contentDescription = "",
                    modifier = Modifier.size(275.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                )
                {
                    Column(verticalArrangement = Arrangement.SpaceBetween) {
                        Text(
                            text = "Salad Pesto Pizza",
                            color = BlackTextColor,
                            style = Typography.body1,
                            fontSize = 22.sp
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            color = Orange500,
                                            fontStyle = Typography.body1.fontStyle,
                                            fontSize = 20.sp
                                        )
                                    ) {
                                        append(
                                            "$"
                                        )
                                    }

                                    withStyle(
                                        style = SpanStyle(
                                            color = BlackTextColor,
                                            fontStyle = Typography.body1.fontStyle,
                                            fontSize = 28.sp
                                        )
                                    ) {
                                        append(
                                            "10.55"
                                        )
                                    }
                                }
                            )
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        BoxWithRes(
                            resId = R.drawable.minus,
                            description = "Minus",
                            boxSize = 36,
                            iconSize = 24,
                            iconColor = BlackTextColor
                        )

                        Spacer(modifier = Modifier.width(14.dp))

                        Text(
                            text = "01",
                            style = Typography.body2,
                            color = TextColor,
                            fontSize = 18.sp,
                        )

                        Spacer(modifier = Modifier.width(14.dp))

                        BoxWithRes(
                            resId = R.drawable.add,
                            description = "Add",
                            boxSize = 36,
                            iconSize = 24,
                            iconColor = Color.White,
                            bgColor = Yellow500
                        )
                    }

                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form.",
                    style = Typography.h5,
                    color = TextColor,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(CardItemBg)
                        .padding(15.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.calori),
                            contentDescription = "Calorie",
                            modifier = Modifier.size(20.dp)
                        )

                        Divider(color = DividerColor,modifier = Modifier.fillMaxHeight().width(1.dp))

                        Image(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "Calorie",
                            modifier = Modifier.size(20.dp)
                        )

                        Divider(color = DividerColor,modifier = Modifier.fillMaxHeight().width(1.dp))

                        Image(
                            painter = painterResource(id = R.drawable.schedule),
                            contentDescription = "Calorie",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Header() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 13.dp)
    ) {
        BoxWithRes(resId = R.drawable.menu, description = "Menu")

        Row(verticalAlignment = Alignment.CenterVertically)
        {
            Icon(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "Location",
                modifier = Modifier.size(16.dp),
                tint = Orange500
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "California, US")
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.arrow_down),
                contentDescription = "Arrow down",
                modifier = Modifier.size(16.dp),
                tint = Orange500
            )
        }

        BoxWithRes(resId = R.drawable.search, description = "Search")

    }
}

@Composable
fun DetailHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        BoxWithRes(resId = R.drawable.arrow_left, description = "Menu")

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(CardItemBg),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.bag),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp),
                    tint = IconColor
                )

                Box(
                    modifier = Modifier
                        .padding(top = 5.dp, end = 5.dp)
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .align(Alignment.TopEnd),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(Yellow500)
                    )
                }
            }
        }
    }
}

@Composable
fun BoxWithRes(
    resId: Int,
    description: String,
    bgColor: Color? = CardItemBg,
    iconColor: Color? = IconColor,
    boxSize: Int? = 40,
    iconSize: Int? = 24
) {
    Box(
        modifier = Modifier
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
            .padding(24.dp)
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
                })

                Box(
                    modifier = Modifier
                        .size(width = 126.dp, height = 40.dp)
                        .clip(
                            RoundedCornerShape(10.dp)
                        )
                        .background(Yellow500),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Order Now",
                        style = Typography.body1,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
            }

            Image(
                painter = painterResource(id = R.drawable.man),
                contentDescription = "Man",
                modifier = Modifier.size(156.dp)
            )

        }
    }
}

@Composable
fun CategoryList(categories: List<CategoryData>) {

    val selectedIndex = remember {
        mutableStateOf(0)
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 13.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(categories.size) { index ->
            CategoryItem(
                categoryData = categories[index],
                selectedIndex = selectedIndex,
                index = index
            )
        }
    }
}

@Composable
fun CategoryItem(categoryData: CategoryData, selectedIndex: MutableState<Int>, index: Int) {

    Box(
        modifier = Modifier
            .size(width = 106.dp, height = 146.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                selectedIndex.value = index
            }
            .background(
                if (selectedIndex.value == index) Yellow500 else CardItemBg
            ), contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = categoryData.resId),
                contentDescription = categoryData.title,
                modifier = Modifier.size(48.dp),
                tint = if (selectedIndex.value == index) Color.White else BlackTextColor

            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = categoryData.title,
                style = Typography.body2,
                color = if (selectedIndex.value == index) Color.White else BlackTextColor
            )
        }
    }

}

@Composable
fun PopularList(popularList: List<PopularData>, navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(popularList.size) { index ->
            PopularItem(popularData = popularList[index], navController = navController)
        }
    }
}

@Composable
fun PopularItem(popularData: PopularData, navController: NavController) {
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
                .clickable {
                    navController.currentBackStackEntry?.arguments = Bundle().apply {
                        putParcelable(Destinations.DetailArgs.foodData, popularData)
                    }
                    navController.navigate(Destinations.Detail)
                }
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
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Orange500,
                                    fontStyle = Typography.body1.fontStyle,
                                    fontSize = 14.sp
                                )
                            ) {
                                append(
                                    "$"
                                )
                            }

                            withStyle(
                                style = SpanStyle(
                                    color = BlackTextColor,
                                    fontStyle = Typography.body1.fontStyle,
                                    fontSize = 20.sp
                                )
                            ) {
                                append(
                                    popularData.price.toString()
                                )
                            }
                        })
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
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FoodDeliveryTheme {
        val navController = rememberNavController()
        DetailScreen(navController = navController)
    }
}