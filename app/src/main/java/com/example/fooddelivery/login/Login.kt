package com.example.fooddelivery.login

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.example.fooddelivery.R
import com.example.fooddelivery.ui.theme.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

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
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .testTag("password"),
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

/*
        Row(modifier = Modifier.padding(start = 55.dp, end = 55.dp, top = 40.dp)) {
            Box(modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .size(55.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication =
                    rememberRipple(bounded = true),
                    onClick = {


                    }
                ),
                contentAlignment = Alignment.Center)
            {
                Image(painter = painterResource(id = R.drawable.google), contentDescription = ""
                )
            }

            Spacer(modifier = Modifier.size(60.dp))

            Box(modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .size(55.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication =
                    rememberRipple(bounded = true),
                    onClick = {

                    }
                ),
                contentAlignment = Alignment.Center)
            {
                Image(painter = painterResource(id = R.drawable.facebook), contentDescription = ""
                )
            }

        }
*/
    }
}