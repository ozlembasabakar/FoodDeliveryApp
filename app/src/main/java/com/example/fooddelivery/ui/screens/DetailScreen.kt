package com.example.fooddelivery.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import com.example.fooddelivery.R
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fooddelivery.data.PopularData
import com.example.fooddelivery.ui.theme.*

private fun mToast(context: Context) {
    Toast.makeText(context, "You can order up to 10", Toast.LENGTH_LONG).show()
}


@Composable
fun DetailScreen(navController: NavController) {

    val count = remember { mutableStateOf(0) }
    val mContext = LocalContext.current

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
            //TODO: show only design screen
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {
                DetailHeader(navController = navController)

                Spacer(modifier = Modifier.height(32.dp))

                Image(
                    painter = painterResource(id = data.resId),
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
                            text = data.title,
                            color = BlackTextColor,
                            style = Typography.body1,
                            fontSize = 22.sp
                        )

                        Row(verticalAlignment = Alignment.CenterVertically)
                        {
                            Text(
                                text = "$",
                                style = Typography.body1,
                                fontSize = 14.sp,
                                color = Orange500
                            )

                            Text(
                                text = data.price.toString(),
                                style = Typography.body1,
                                fontSize = 20.sp,
                                color = BlackTextColor
                            )
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        BoxWithResCalc(
                            resId = R.drawable.minus,
                            description = "Minus",
                            iconColor = BlackTextColor,
                            boxSize = 36,
                            iconSize = 14
                        ) {
                            if (count.value >= 1) count.value--
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
                            if (count.value <= 9) count.value++ else mToast(context = mContext)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = data.description,
                    style = Typography.h5,
                    color = TextColor,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                FoodDetailBox(data = data)

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Ingredients",
                    style = Typography.body1,
                    fontSize = 22.sp,
                    color = BlackTextColor,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(data.ingredients.size) { index ->
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    CardItemBg
                                ), contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = data.ingredients[index]),
                                contentDescription = "Onion",
                                modifier = Modifier.size(width = 30.dp, height = 24.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .size(width = 203.dp, height = 56.dp)
                        .clip(RoundedCornerShape(topEnd = 18.dp, topStart = 18.dp))
                        .background(
                            Yellow500
                        ), contentAlignment = Alignment.Center
                ) {
                    Text(text = "Add to card", style = Typography.body1, color = Color.White)
                }
            }
        }
    }
}


@Composable
fun DetailHeader(navController: NavController) {
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
fun FoodDetailBox(data: PopularData) {
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

            Row {
                Image(
                    painter = painterResource(id = R.drawable.calori),
                    contentDescription = "Calorie",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "${data.calorie} Kcal",
                    style = Typography.body2,
                    color = BlackTextColor
                )
            }

            Divider(
                color = DividerColor, modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )

            Row {
                Image(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "Star",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = data.rate.toString(),
                    style = Typography.body2,
                    color = BlackTextColor
                )
            }

            Divider(
                color = DividerColor, modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )

            Row {
                Image(
                    painter = painterResource(id = R.drawable.schedule),
                    contentDescription = "Time",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "${data.scheduleTime} Min.",
                    style = Typography.body2,
                    color = BlackTextColor
                )

            }
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
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = bgColor!!),
        contentPadding = PaddingValues(
            start = 10.dp,
            top = 10.dp,
            end = 10.dp,
            bottom = 10.dp
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
    resId: Int,
    description: String,
    bgColor: Color? = CardItemBg,
    iconColor: Color? = IconColor,
    boxSize: Int? = 40,
    iconSize: Int? = 24,
    navController: NavController? = null
) {
    Box(
        modifier = Modifier
            .size(boxSize!!.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                navController?.navigateUp()
            }
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