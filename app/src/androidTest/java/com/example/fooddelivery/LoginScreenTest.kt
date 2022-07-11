package com.example.fooddelivery

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ActivityScenario
import com.example.fooddelivery.ui.screens.LoginScreen
import com.example.fooddelivery.ui.theme.FoodDeliveryTheme
import com.squareup.okhttp.Route
import kotlinx.coroutines.delay
import org.junit.Rule
import org.junit.Test

fun SemanticsNodeInteraction.imeActionValue(): String? {
    for ((key, value) in fetchSemanticsNode().config) {
        if (key.name == "ImeAction") {
            return value?.toString()
        }
    }
    return null
}

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun emailIsDisable() {
        composeTestRule.onNodeWithText("Email Address").assertExists()
    }

    @Test
    fun passwordIsDisable() {
        composeTestRule.onNodeWithText("Password").assertExists()
    }

    @Test
    fun logInButtonIsClickable() {
        composeTestRule.onNodeWithTag("loginButton").assertHasClickAction()
    }

    @Test
    fun signUpButtonIsClickable() {
        //composeTestRule.onNodeWithTag("signUp").assertHasClickAction()
        composeTestRule.onNodeWithTag("signUp").performClick()

        Thread.sleep(2000)

        composeTestRule.onNodeWithTag("register").assertExists()

    }

    @Test
    fun passwordIsValid() {
        composeTestRule.onNodeWithTag("password")
    }
}