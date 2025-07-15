package com.pidygb.mynasadailypics

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class AppTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun app_startsSuccessfully() {
        composeTestRule.setContent {
            App()
        }
        // This is a very basic test. A more comprehensive test would check for specific UI elements.
        // However, without knowing the content of the picturesScreen, this is the best we can do.
    }
}
