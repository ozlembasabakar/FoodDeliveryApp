package com.example.fooddelivery.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import com.example.fooddelivery.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fooddelivery.viewmodel.LoginViewModel
import com.example.fooddelivery.viewmodel.ContactViewModel
import com.example.fooddelivery.data.User
import com.example.fooddelivery.ui.theme.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private val auth by lazy {
    Firebase.auth
}

@Composable
fun LoginScreen(navController: NavController, auth: FirebaseAuth) {

    val TAG = User.TAG

    val contactViewModel: ContactViewModel = viewModel()
    val loginViewModel: LoginViewModel = viewModel()

    val viewStateLogin by loginViewModel.viewStateLogin.observeAsState()

    val focusManager = LocalFocusManager.current

    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

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
                //.height(200.dp)
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
                            .clickable {
                                // room'a kaydetme kısmı
/*                        if (name.isNotEmpty() and surname.isNotEmpty() and email.isNotEmpty() and phone.isNotEmpty() and password.isNotEmpty()) {
                            customerViewModel.insertCustomer((
                                    CustomerItem(name, surname, email, password, phone)
                                    ))
                        }*/

                                navController.currentBackStackEntry?.arguments
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
                                navController.currentBackStackEntry?.arguments
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
            OutlinedTextField(
                value = viewStateLogin!!.email.text.value, onValueChange = { viewStateLogin!!.email.onTextChanged(it) },
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
                value = viewStateLogin!!.password.text.value, onValueChange = { viewStateLogin!!.password.onTextChanged(it) },
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
                isError = !viewStateLogin!!.isPasswordValid,
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
                .fillMaxWidth()
                .padding(start = 25.dp)
                .clickable {
                    navController.currentBackStackEntry?.arguments
                    navController.navigate(Destinations.ForgotPassword)
                }
        )

        Spacer(modifier = Modifier.height(25.dp))


        Button(
            onClick = {
                auth.signInWithEmailAndPassword(viewStateLogin!!.email.text.value, viewStateLogin!!.password.text.value)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Log.d(TAG, "User logged in successfully.")
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
                .clip(RoundedCornerShape(30.dp)),
            colors = ButtonDefaults.buttonColors(backgroundColor = Yellow500),
            enabled = viewStateLogin!!.isValidEmail && viewStateLogin!!.isPasswordValid,
        ) {
            Text(
                text = "Login", color = Color.White, style = Typography.body1
            )
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