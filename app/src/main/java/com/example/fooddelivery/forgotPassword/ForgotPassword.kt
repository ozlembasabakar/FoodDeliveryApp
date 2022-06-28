package com.example.fooddelivery.forgotPassword

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fooddelivery.Destinations
import com.example.fooddelivery.R
import com.example.fooddelivery.ui.theme.*
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ForgotPasswordScreen(navController: NavController) {

    val forgotPasswordViewModel: ForgotPasswordViewModel = viewModel()

    val viewStateForgotPassword by forgotPasswordViewModel.viewStateForgotPassword.observeAsState()

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .background(CardItemBg)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
                )
                .background(Color.White)
                .padding(start = 25.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text(
                    text = "Forgot password?",
                    color = Yellow500,
                    style = Typography.h6,
                    modifier = Modifier.padding(end = 40.dp)
                )
            }

        }

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            Modifier
                .background(CardItemBg)
                .padding(start = 25.dp, end = 25.dp)
        ) {

            Spacer(modifier = Modifier.height(120.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
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

                    Box(modifier = Modifier.padding(
                        end = 8.dp,
                        top = 2.dp,
                        bottom = 10.dp,
                        start = 8.dp)) {
                        OutlinedTextField(
                            value = viewStateForgotPassword!!.email.text.value,
                            onValueChange = { viewStateForgotPassword!!.email.onTextChanged(it) },
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
                    }
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

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
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication =
                        rememberRipple(bounded = true),
                        onClick = {
                            if (viewStateForgotPassword!!.isInvalidEmail) {
                                Toast
                                    .makeText(context,
                                        "Please enter a valid email",
                                        Toast.LENGTH_LONG)
                                    .show()
                            } else {
                                FirebaseAuth
                                    .getInstance()
                                    .sendPasswordResetEmail(viewStateForgotPassword!!.email.text.value)
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Toast
                                                .makeText(context,
                                                    "Email send successfully to reset your password",
                                                    Toast.LENGTH_LONG)
                                                .show()
                                        } else {
                                            Toast
                                                .makeText(context,
                                                    task.exception!!.message.toString(),
                                                    Toast.LENGTH_LONG)
                                                .show()
                                        }
                                    }
                            }
                        }
                    )
            )

            Spacer(modifier = Modifier.height(32.dp))

            Box(
                Modifier
                    .padding(end = 16.dp)
                    .align(Alignment.End)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication =
                        rememberRipple(bounded = true),
                        onClick = {
                            navController.currentBackStackEntry?.arguments
                            navController.navigate(Destinations.Login)
                        }
                    )
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