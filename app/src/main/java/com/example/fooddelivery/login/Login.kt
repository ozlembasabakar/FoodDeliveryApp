package com.example.fooddelivery.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fooddelivery.Destinations
import com.example.fooddelivery.login.LoginViewModel
import com.example.fooddelivery.login.User
import com.example.fooddelivery.ui.theme.*
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navController: NavController, auth: FirebaseAuth) {

    val TAG = User.TAG

    val loginViewModel: LoginViewModel = hiltViewModel()

    val viewStateLogin by loginViewModel.viewStateLogin.observeAsState()

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .background(CardItemBg)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
                )
                .background(Color.White)
        ) {
            Column {
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
                            .clip(RoundedCornerShape(10.dp))
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication =
                                rememberRipple(bounded = true),
                                onClick = {
                                    navController.currentBackStackEntry?.arguments
                                    navController.navigate(Destinations.Login)
                                }
                            ),
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
                            .clip(RoundedCornerShape(10.dp))
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication =
                                rememberRipple(bounded = true),
                                onClick = {
                                    navController.currentBackStackEntry?.arguments
                                    navController.navigate(Destinations.SignUp)
                                }
                            )
                            .testTag("signUp"),
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
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp, top = 50.dp)
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
        ) {
            OutlinedTextField(
                value = viewStateLogin!!.email.text.value,
                onValueChange = { viewStateLogin!!.email.onTextChanged(it) },
                placeholder = {
                    Text(
                        text = "Email Address",
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
                ),
                isError = !viewStateLogin!!.isValidEmail and viewStateLogin!!.email.text.value.isNotBlank(),
                trailingIcon = {
                    when {
                        viewStateLogin!!.email.text.value.isNotBlank() ->
                            IconButton(onClick = { viewStateLogin!!.email.text.value = "" }) {
                                Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
                            }
                    }
                }
            )
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
            OutlinedTextField(
                value = viewStateLogin!!.password.text.value,
                onValueChange = { viewStateLogin!!.password.onTextChanged(it) },
                placeholder = {
                    Text(
                        text = "Password",
                        color = Color(0xFFA0A0A0).copy(alpha = 0.6f)
                    )
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().testTag("password"),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                ),
                textStyle = TextStyle(
                    color = Color(0xFFA0A0A0).copy(alpha = 0.6f),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    fontFamily = nunito
                ),
                isError = !viewStateLogin!!.isPasswordValid and viewStateLogin!!.password.text.value.isNotBlank(),
                trailingIcon = {
                    IconButton(onClick = { loginViewModel.togglePasswordVisibilityLogin() }) {
                        Icon(
                            imageVector = if (viewStateLogin!!.isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = ""
                        )
                    }
                },
                visualTransformation = if (viewStateLogin!!.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Forgot password?",
            style = Typography.h4,
            color = Orange500,
            modifier = Modifier
                .wrapContentWidth()
                .padding(start = 25.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication =
                    rememberRipple(bounded = true),
                    onClick = {
                        navController.currentBackStackEntry?.arguments
                        navController.navigate(Destinations.ForgotPassword)
                    }
                )
                .align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                auth.signInWithEmailAndPassword(viewStateLogin!!.email.text.value,
                    viewStateLogin!!.password.text.value)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Log.d(TAG, "User logged in successfully.")
                            loginViewModel.saveUserToDb(it.result!!.user!!.email!!)
                            loginViewModel.resetFavorites()
                            navController.currentBackStackEntry?.arguments
                            navController.navigate(Destinations.Home)
                        } else {
                            Log.w(TAG, "LOGIN FAILED!", it.exception)
                        }
                    }
            },
            modifier = Modifier
                .padding(start = 45.dp, end = 45.dp)
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(30.dp))
                .testTag("loginButton"),
            colors = ButtonDefaults.buttonColors(backgroundColor = Yellow500),
            enabled = viewStateLogin!!.isValidEmail && viewStateLogin!!.isPasswordValid,

        ) {
            Text(
                text = "Login", color = Color.White, style = Typography.body1
            )
        }

        Spacer(modifier = Modifier.height(15.dp))
    }
}