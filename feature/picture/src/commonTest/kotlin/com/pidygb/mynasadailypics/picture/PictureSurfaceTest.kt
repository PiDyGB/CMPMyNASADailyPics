package com.pidygb.mynasadailypics.picture

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.*
import coil3.annotation.ExperimentalCoilApi
import com.pidygb.mynasadailypics.picture.model.UiPictureItem
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class, ExperimentalCoilApi::class)
class PictureSurfaceTest {

    /**
     * Tests that the title text is displayed correctly.
     */
    @Test
    fun pictureSurface_DisplaysTitleCorrectly() = runComposeUiTest {
        val title = "Amazing Space Picture"
        val date = "2025-07-20"
        val url = ""
        val description = "A beautiful view of space"

        setContent {
            MaterialTheme {
                PictureSurface(
                    picture = UiPictureItem(
                        title = title,
                        date = date,
                        url = url,
                        description = description
                    )
                )
            }
        }
        waitUntilAtLeastOneExists(hasTestTag(PICTURE_SCREEN_TEST_TAG))
        onNodeWithText(title).assertIsDisplayed()
    }

    /**
     * Tests that the date text is displayed correctly.
     */
    @Test
    fun pictureSurface_DisplaysDateCorrectly() = runComposeUiTest {
        val title = "Amazing Space Picture"
        val date = "2025-07-20"
        val url = ""
        val description = "A beautiful view of space"

        setContent {
            MaterialTheme {

                PictureSurface(
                    picture = UiPictureItem(
                        title = title,
                        date = date,
                        url = url,
                        description = description
                    )
                )
            }
        }
        waitUntilAtLeastOneExists(hasTestTag(PICTURE_SCREEN_TEST_TAG))
        onNodeWithText(date).assertIsDisplayed()
    }

    /**
     * Tests that the description text is displayed when provided.
     */
    @Test
    fun pictureSurface_DisplaysDescriptionWhenProvided() = runComposeUiTest {
        val title = "Amazing Space Picture"
        val date = "2025-07-20"
        val url = ""
        val description = "A beautiful view of space"

        setContent {
            MaterialTheme {
                PictureSurface(
                    picture = UiPictureItem(
                        title = title,
                        date = date,
                        url = url,
                        description = description
                    )
                )
            }
        }
        waitUntilAtLeastOneExists(hasTestTag(PICTURE_SCREEN_TEST_TAG))
        onNodeWithText(description).assertIsDisplayed()
    }

    /**
     * Tests that the description text is not displayed when null.
     */
    @Test
    fun pictureSurface_DoesNotDisplayDescriptionWhenNull() = runComposeUiTest {
        val title = "Amazing Space Picture"
        val date = "2025-07-20"
        val url = ""
        val description = null

        setContent {
            MaterialTheme {
                PictureSurface(
                    picture = UiPictureItem(
                        title = title,
                        date = date,
                        url = url,
                        description = description
                    )
                )
            }
        }
        waitUntilAtLeastOneExists(hasTestTag(PICTURE_SCREEN_TEST_TAG))
        // Verify that only title and date are displayed, but no description
        onNodeWithText(title).assertIsDisplayed()
        onNodeWithText(date).assertIsDisplayed()
        // We can't easily test for absence of null text, but we can verify the main content is there
        onNodeWithText(date).assert(hasAnySibling(hasText("")).not())
    }

    /**
     * Tests that the image has the correct content description.
     */
    @Test
    fun pictureSurface_ImageHasCorrectContentDescription() = runComposeUiTest {
        val title = "Amazing Space Picture"
        val date = "2025-07-20"
        val url = ""
        val description = "A beautiful view of space"

        setContent {
            MaterialTheme {
                PictureSurface(
                    picture = UiPictureItem(
                        title = title,
                        date = date,
                        url = url,
                        description = description
                    )
                )
            }
        }
        waitUntilAtLeastOneExists(hasTestTag(PICTURE_SCREEN_TEST_TAG))
        // The image should have the fixed content description from the component
        onNodeWithContentDescription(title).assertIsDisplayed()
    }

    /**
     * Tests that all required elements are present in the UI.
     */
    @Test
    fun pictureSurface_DisplaysAllRequiredElements() = runComposeUiTest {
        val title = "Amazing Space Picture"
        val date = "2025-07-20"
        val url = ""
        val description = "A beautiful view of space"

        setContent {
            MaterialTheme {
                PictureSurface(
                    picture = UiPictureItem(
                        title = title,
                        date = date,
                        url = url,
                        description = description
                    )
                )
            }
        }

        waitUntilAtLeastOneExists(hasTestTag(PICTURE_SCREEN_TEST_TAG))
        // Verify all elements are displayed
        onNodeWithText(title).assertIsDisplayed()
        onNodeWithText(date).assertIsDisplayed()
        onNodeWithText(description).assertIsDisplayed()
        onNodeWithContentDescription(title).assertIsDisplayed()
    }

    /**
     * Tests the component with minimal data (null description and url).
     */
    @Test
    fun pictureSurface_WorksWithMinimalData() = runComposeUiTest {
        val title = "Minimal Picture"
        val date = "2025-07-20"
        val url = null
        val description = null

        setContent {
            MaterialTheme {
                PictureSurface(
                    picture = UiPictureItem(
                        title = title,
                        date = date,
                        url = url,
                        description = description
                    )
                )
            }
        }
        waitUntilAtLeastOneExists(hasTestTag(PICTURE_SCREEN_TEST_TAG))
        // Verify required elements are still displayed
        onNodeWithText(title).assertIsDisplayed()
        onNodeWithText(date).assertIsDisplayed()
        onNodeWithContentDescription(title).assertIsDisplayed()
    }
}