package com.pidygb.feature.picture

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.pidygb.mynasadailypics.picture.PictureSurface
import com.pidygb.mynasadailypics.picture.model.UiPictureItem
import org.junit.Rule
import org.junit.Test

class PictureSurfaceTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun pictureSurface_displaysData() {
        val picture = UiPictureItem(
            title = "The Title",
            date = "04/07/2025",
            url = "https://search.yahoo.com/search?p=neglegentur",
            description = "The long description"
        )

        composeTestRule.setContent {
            PictureSurface(picture = picture)
        }

        composeTestRule.onNodeWithText("The Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("04/07/2025").assertIsDisplayed()
        composeTestRule.onNodeWithText("The long description").assertIsDisplayed()
    }
}
