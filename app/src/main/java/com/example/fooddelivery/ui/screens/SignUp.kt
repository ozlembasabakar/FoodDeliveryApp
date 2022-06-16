package com.example.fooddelivery.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import com.example.fooddelivery.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fooddelivery.ui.theme.*

@Composable
fun SignUpScreen(navController: NavController) {

    var name by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    var number by remember {
        mutableStateOf("")
    }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LoginBg),
        horizontalAlignment = Alignment.CenterHorizontally,
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
                        .size(height = 206.dp, width = 307.dp)
                        .padding(start = 85.dp)
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

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text(
                text = "Register",
                color = Yellow500,
                style = Typography.h6,
                modifier = Modifier.padding(end = 40.dp)
            )

            Row {
                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "",
                    modifier = Modifier.size(40.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "",
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column {
            Card(
                modifier = Modifier
                    .padding(start = 25.dp, end = 25.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
            ) {
                OutlinedTextField(
                    value = name, onValueChange = { name = it },
                    placeholder = {
                        Text(
                            text = "Full Name",
                            color = Color(0xFFA0A0A0).copy(alpha = 0.6f)
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    ),
                    textStyle = TextStyle(
                        color = Color(0xFFA0A0A0),
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        fontFamily = nunito
                    )
                )
                //Text(text = "Enter your mail address")
            }

            Card(
                modifier = Modifier
                    .padding(start = 25.dp, end = 25.dp, top = 20.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
            ) {
                OutlinedTextField(
                    value = number, onValueChange = { number = it },
                    placeholder = {
                        Text(
                            text = "Mobile Number",
                            color = Color(0xFFA0A0A0).copy(alpha = 0.6f)
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
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

            Card(
                modifier = Modifier
                    .padding(start = 25.dp, end = 25.dp, top = 20.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
            ) {
                OutlinedTextField(
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

            Card(
                modifier = Modifier
                    .padding(start = 25.dp, end = 25.dp, top = 20.dp, bottom = 30.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
            ) {
                OutlinedTextField(
                    value = password, onValueChange = { password = it },
                    placeholder = {
                        Text(
                            text = "Confirm Password",
                            color = Color(0xFFA0A0A0).copy(alpha = 0.6f)
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
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
                //Text(text = "Enter your mail address")
            }
        }

        Row(Modifier.padding(start = 25.dp, end = 25.dp)) {
            Box(
                modifier = Modifier
                    .size(height = 58.dp, width = 185.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(Yellow500),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Sign Up", color = Color.White)
            }

            Spacer(modifier = Modifier.padding(end = 20.dp))

            val annotatedText = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFFB3B3B3),
                        fontWeight = FontWeight.ExtraLight,
                        fontFamily = nunito,
                        fontSize = 16.sp
                    )
                ) {
                    append("Already a \nMember? ")
                }
                pushStringAnnotation(
                    tag = "URL",
                    annotation = "https://developer.android.com"
                )
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFFB3B3B3),
                        fontFamily = nunito,
                        fontSize = 16.sp
                    )
                ) {
                    append(" Login")
                }
            }

            ClickableText(
                text = annotatedText,
                onClick = { offset ->
                    // We check if there is an *URL* annotation attached to the text
                    // at the clicked position
                    annotatedText.getStringAnnotations(
                        tag = "URL", start = offset,
                        end = offset
                    )
                        .firstOrNull()?.let { annotation ->
                            // If yes, we log its value
                            Log.d("Clicked URL", annotation.item)
                        }
                },
            )
        }
    }
}
