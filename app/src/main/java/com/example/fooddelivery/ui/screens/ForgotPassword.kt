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
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun ForgotPasswordScreen(navController: NavController) {

    var email by remember {
        mutableStateOf("")
    }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .background(LoginBg)
            .fillMaxSize(),
        //horizontalAlignment = Alignment.CenterHorizontally,
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
            Column(modifier = Modifier) {
/*                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(height = 226.dp, width = 327.dp)
                        .padding(start = 25.dp)
                )*/

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 45.dp, end = 45.dp)
                ) {

                    Column(
                        Modifier.width(134.dp),
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
                        Modifier.width(134.dp),
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

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            Modifier
                .background(LoginBg)
                .padding(start = 25.dp, end = 25.dp)
        ) {
            Box(Modifier.size(22.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        navController.currentBackStackEntry?.arguments =
                            Bundle().apply {

                            }
                        navController.navigate(Destinations.Login)
                    }
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            Text(text = "Forgot\npassword?", color = Yellow500, style = Typography.h6)

            Spacer(modifier = Modifier.height(28.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.mail),
                        contentDescription = ""
                    )

                    OutlinedTextField(
                        value = email, onValueChange = { email = it },
                        label = {
                            Text(
                                text = "Email Address",
                                color = Color(0xFFA0A0A0).copy(alpha = 0.6f)
                            )
                        },
                        placeholder = { Text(text = "abc@domain.com") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.clearFocus() }
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White
                        ),
                        textStyle = TextStyle(
                            color = TextField.copy(alpha = 0.6f),
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        ),
                    )
                    //Text(text = "Enter your mail address")
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Row {
                Text(
                    text = "* ",
                    style = Typography.h4,
                    color = Orange500
                )
                Text(
                    text = "We will you a message to set or reset your new password",
                    fontSize = 12.sp,
                    color = Color(0xFF676767),
                    fontWeight = FontWeight.Normal
                )

            }

            Spacer(modifier = Modifier.height(34.dp))

            Text(
                text = "Send Code",
                fontSize = 24.sp,
                color = Color(0xFFB2B2B2),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))

            Box(
                Modifier
                    .padding(end = 16.dp)
                    .align(Alignment.End)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_forward),
                    contentDescription = "", Modifier.size(50.dp)
                )
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}