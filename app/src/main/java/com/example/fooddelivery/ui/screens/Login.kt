package com.example.fooddelivery.ui.screens

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import com.example.fooddelivery.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fooddelivery.ui.theme.*

@Composable
fun LoginScreenCard(
    height: Int,
    text: String,
    backgroundColor: Color,
    textColor: Color,

    ) {

    Box(
        modifier = Modifier
            .padding(start = 45.dp, end = 45.dp)
            .fillMaxWidth()
            .height(height.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(backgroundColor), contentAlignment = Alignment.Center


    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = text, color = textColor, style = Typography.body1
            )
        }
    }
}

@Composable
fun LoginScreen(navController: NavController) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .background(Color.LightGray.copy(alpha = 0.2f))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                //.height(200.dp)
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
                )
                .background(Color.White)
        ) {
            Column {
/*                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(height = 246.dp, width = 347.dp)
                        .padding(start = 25.dp)
                )*/

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 45.dp, end = 45.dp, top = 45.dp)
                ) {

                    Column(
                        Modifier
                            .width(134.dp)
                            .clickable {
                                navController.currentBackStackEntry?.arguments =
                                    Bundle().apply {

                                    }
                                navController.navigate(Destinations.Login)
                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Log In")
                        Box(
                            Modifier
                                .clip(RoundedCornerShape(30.dp))
                                .background(Yellow500)
                                .height(3.dp)
                                .fillMaxWidth()
                        )
                    }

                    Column(
                        Modifier
                            .width(134.dp)
                            .clickable {
                                navController.currentBackStackEntry?.arguments =
                                    Bundle().apply {

                                    }
                                navController.navigate(Destinations.SignUp)
                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Sign Up")
                        Box(
                            Modifier
                                .clip(RoundedCornerShape(30.dp))
                                .background(Yellow500)
                                .height(3.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }

        Card(
            //text = "hello",
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp, top = 50.dp)
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
        ) {
            TextField(
                value = email, onValueChange = { email = it },
                placeholder = {
                    Text(
                        text = "Username, Mobile Number",
                        color = Color(0xFFA0A0A0).copy(alpha = 0.6f)
                    )
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                ),
                textStyle = TextStyle(
                    color = Color(0xFFA0A0A0).copy(alpha = 0.6f),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    fontFamily = nunito
                )
            )
            //Text(text = "Enter your mail address")
        }


        Spacer(modifier = Modifier.height(18.dp))

        Card(
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp, top = 18.dp)
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
        ) {
            TextField(
                value = password, onValueChange = { password = it },
                placeholder = {
                    Text(
                        text = "Password",
                        color = Color(0xFFA0A0A0).copy(alpha = 0.6f)
                    )
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.clearFocus() }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                ),
                textStyle = TextStyle(
                    color = Color(0xFFA0A0A0).copy(alpha = 0.6f),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    fontFamily = nunito
                )
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Forgot password?",
            style = Typography.h4,
            color = Orange500,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp)
                .clickable {
                    navController.currentBackStackEntry?.arguments = Bundle().apply {

                    }
                    navController.navigate(Destinations.ForgotPassword)
                }

        )

        Spacer(modifier = Modifier.height(25.dp))

        Box(modifier = Modifier.clickable {
            navController.currentBackStackEntry?.arguments =
                Bundle().apply {

                }
            navController.navigate(Destinations.Home)
        }
        ) {
            LoginScreenCard(56, "Login", Yellow500, Color.White)
        }
        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Or",
            modifier = Modifier.size(height = 27.dp, width = 22.dp),
            style = Typography.body1
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                )

                Spacer(modifier = Modifier.width(40.dp))

                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                )
            }
        }
    }
}