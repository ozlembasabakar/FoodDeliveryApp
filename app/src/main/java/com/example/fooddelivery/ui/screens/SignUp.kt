package com.example.fooddelivery.ui.screens

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import com.example.fooddelivery.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fooddelivery.MainActivity
import com.example.fooddelivery.ui.composables.CustomOutlinedTextField
import com.example.fooddelivery.ui.theme.*
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

@Composable
fun SignUpScreen(navController: NavController) {

    var name by rememberSaveable { mutableStateOf("") }
    var surname by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmedPassword by rememberSaveable { mutableStateOf("") }

    var validateName by rememberSaveable { mutableStateOf(true) }
    var validateSurname by rememberSaveable { mutableStateOf(true) }
    var validateEmail by rememberSaveable { mutableStateOf(true) }
    var validatePhone by rememberSaveable { mutableStateOf(true) }
    var validatePassword by rememberSaveable { mutableStateOf(true) }
    var validateConfirmedPassword by rememberSaveable { mutableStateOf(true) }
    var validateArePasswordsEquals by rememberSaveable { mutableStateOf(true) }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
    var isConfirmedPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val validateNameError = "Please input a valid name"
    val validateSurnameError = "Please input a valid surname"
    val validateEmailError = "The format of the email doesn't seem right"
    val validatePhoneError = "The format of the phone number doesn't seem right"
    val validatePasswordError =
        "Must mix capital and non-capital letters, a number, spacial character and min. length of 8"
    val validateEqualPasswordError = "Passwords must be equal"

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    fun validateData(
        name: String,
        surname: String,
        email: String,
        phone: String,
        password: String,
        confirmedPassword: String
    ): Boolean {
        val passwordRegex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^!/&+=]).{8,}".toRegex()

        validateName = name.isNotBlank()
        validateSurname = surname.isNotBlank()
        validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        validatePhone = Patterns.PHONE.matcher(phone).matches()
        validatePassword = passwordRegex.matches(password)
        validateConfirmedPassword = passwordRegex.matches(confirmedPassword)
        validateArePasswordsEquals = password == confirmedPassword

        return validateName && validateSurname && validateEmail && validatePhone && validatePassword && validateConfirmedPassword && validateArePasswordsEquals
    }

    fun register(
        name: String,
        surname: String,
        email: String,
        phone: String,
        password: String,
        confirmedPassword: String
    ) {
        if (validateData(name, surname, email, phone, password, confirmedPassword)) {
            // Registration logic
            Log.d(
                MainActivity::class.java.simpleName,
                "Name: $name, Surname: $surname, Email: $email, Password: $password"
            )
            navController.currentBackStackEntry?.arguments =
                Bundle().apply {

                }
            navController.navigate(Destinations.Login)
        } else {
            Toast.makeText(context, "Please, review fields", Toast.LENGTH_LONG).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CardItemBg),
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

        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomOutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = "Name",
                showError = !validateName,
                errorMessage = validateNameError,
                leadingIconImageVector = Icons.Default.PermIdentity,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            CustomOutlinedTextField(
                value = surname,
                onValueChange = { surname = it },
                label = "Surname",
                showError = !validateSurname,
                errorMessage = validateSurnameError,
                leadingIconImageVector = Icons.Default.PermIdentity,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            CustomOutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email Address",
                showError = !validateEmail,
                errorMessage = validateEmailError,
                leadingIconImageVector = Icons.Default.AlternateEmail,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            CustomOutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = "Phone Number",
                showError = !validatePhone,
                errorMessage = validatePhoneError,
                leadingIconImageVector = Icons.Default.Phone,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            CustomOutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                showError = !validatePassword,
                errorMessage = validatePasswordError,
                isPasswordField = true,
                isPasswordVisible = isPasswordVisible,
                onVisibilityChange = { isPasswordVisible = it },
                leadingIconImageVector = Icons.Default.Password,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            CustomOutlinedTextField(
                value = confirmedPassword,
                onValueChange = { confirmedPassword = it },
                label = "Confirmed Password",
                showError = !validateConfirmedPassword or !validateArePasswordsEquals,
                errorMessage = if (!validateConfirmedPassword) validatePasswordError else validateEqualPasswordError,
                isPasswordField = true,
                isPasswordVisible = isConfirmedPasswordVisible,
                onVisibilityChange = { isConfirmedPasswordVisible = it },
                leadingIconImageVector = Icons.Default.Password,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                )
            )

            Row(Modifier.padding(start = 25.dp, end = 25.dp, bottom = 20.dp, top = 12.dp)) {

                Button(
                    onClick = {
                        register(name, surname, email, phone, password, confirmedPassword)
                    },
                    modifier = Modifier
                        .size(height = 58.dp, width = 185.dp)
                        .clip(RoundedCornerShape(30.dp)),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Yellow500,
                        contentColor = Color.White
                    )
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
}


@Preview
@Composable
fun PreviewSignUpScreen() {
    val navController = rememberNavController()
    SignUpScreen(navController = navController)
}
