package com.pidygb.mynasadailypics.pictures

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.pidygb.mynasadailypics.core.data.PicturesRepository
import com.pidygb.mynasadailypics.core.model.Picture
import com.pidygb.mynasadailypics.core.testing.SetupApplication
import com.pidygb.mynasadailypics.core.ui.pictures.PICTURE_ITEM_IMAGE_TEST_TAG
import com.pidygb.mynasadailypics.core.ui.pictures.PICTURE_ITEM_TEST_TAG
import com.pidygb.mynasadailypics.pictures.di.picturesModule
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class PicturesSurfaceTest : KoinTest {

    // Create a test ViewModelStoreOwner
    private val testViewModelStoreOwner = object : ViewModelStoreOwner {
        override val viewModelStore: ViewModelStore = ViewModelStore()
    }

    private val mockPicturesRepository = module {
        factory<PicturesRepository> {
            mock<PicturesRepository> {
                everySuspend { getPictures() } returns Unit
                every { pictures } returns flow {
                    emit(
                        listOf(
                            Picture(
                                title = "Test Picture",
                                date = "2025-07-22",
                                explanation = "Test explanation",
                                url = "https://www.example.com/test.png",
                                mediaType = "image",
                                hdUrl = "https://www.example.com/test.png",
                                serviceVersion = "Service Version "
                            )
                        )
                    )
                }
            }
        }
    }

    private fun KoinApplication.extraModules(): KoinApplication = modules(picturesModule, mockPicturesRepository)

    /**
     * Tests that the PicturesSurface composable can be created without crashing.
     * This is a basic smoke test to ensure the component renders.
     */
    @Test
    fun picturesSurface_RendersWithoutCrashing() = runComposeUiTest {
        setContent {
            SetupApplication({ extraModules() }) {
                CompositionLocalProvider(
                    LocalViewModelStoreOwner provides testViewModelStoreOwner
                ) {
                    PicturesSurface(
                        onPictureClick = {}
                    )
                }
            }
        }

        waitUntilExactlyOneExists(hasTestTag(PICTURES_SURFACE_TEST_TAG))
        // Verify the main surface exists and is displayed
        onNodeWithTag(PICTURES_SURFACE_TEST_TAG).assertExists()
        onNodeWithTag(PICTURES_SURFACE_TEST_TAG).assertIsDisplayed()

        // Verify that the list is displayed (showing our mock data)
        waitUntilExactlyOneExists(hasTestTag(PICTURES_LIST_TEST_TAG))
        onNodeWithTag(PICTURES_LIST_TEST_TAG).assertExists()
        onNodeWithTag(PICTURES_LIST_TEST_TAG).assertIsDisplayed()

        // Verify that the picture item is displayed
        waitUntil {
            onAllNodesWithTag(PICTURE_ITEM_IMAGE_TEST_TAG, useUnmergedTree = true)
                .fetchSemanticsNodes().size == 1
        }
        onNodeWithText("Test Picture").assertIsDisplayed()
        onNodeWithText("2025-07-22").assertIsDisplayed()
        onNode(
            hasTestTag(PICTURE_ITEM_IMAGE_TEST_TAG)
                .and(hasContentDescription("Test Picture")),
            useUnmergedTree = true
        ).assertIsDisplayed()
    }

    /**
     * Tests that the PicturesSurface accepts the onPictureClick callback.
     */
    @Test
    fun picturesSurface_AcceptsOnPictureClickCallback() = runComposeUiTest {
        var callbackInvoked = false

        setContent {
            SetupApplication({ extraModules() }) {
                CompositionLocalProvider(
                    LocalViewModelStoreOwner provides testViewModelStoreOwner
                ) {
                    PicturesSurface(
                        onPictureClick = {
                            callbackInvoked = true
                        }
                    )
                }
            }
        }

        onRoot().assertExists()
        assertFalse(callbackInvoked)

        onNodeWithTag(PICTURE_ITEM_TEST_TAG).performClick()
        assertTrue(callbackInvoked)
    }

    /**
     * Tests that the PicturesSurface can be created with a modifier.
     */
    @Test
    fun picturesSurface_AcceptsModifier() = runComposeUiTest {
        setContent {
            SetupApplication({ extraModules() }) {
                CompositionLocalProvider(
                    LocalViewModelStoreOwner provides testViewModelStoreOwner
                ) {
                    PicturesSurface(
                        modifier = Modifier.fillMaxSize(),
                        onPictureClick = {}
                    )
                }
            }
        }

        onRoot().assertExists()
        onRoot().assertIsDisplayed()
    }
}