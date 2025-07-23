package com.pidygb.mynasadailypics.picture

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import com.pidygb.mynasadailypics.picture.model.UiPictureItem
import org.jetbrains.compose.ui.tooling.preview.Preview

const val PICTURE_SCREEN_TEST_TAG = "picture_screen_test_tag"

@Composable
fun PictureSurface(
    modifier: Modifier = Modifier,
    picture: UiPictureItem
) {
    Surface(modifier = modifier.fillMaxSize().testTag(PICTURE_SCREEN_TEST_TAG)) {
        Column {
            Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(1 / 2f)) {
                var loading by remember { mutableStateOf(true) }
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = picture.url,
                    contentDescription = picture.title,
                    contentScale = ContentScale.Crop,
                    error = rememberVectorPainter(Icons.Default.BrokenImage),
                    onSuccess = { loading = false }
                )
                androidx.compose.animation.AnimatedVisibility(
                    loading,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    CircularProgressIndicator()
                }
            }

            Column(
                Modifier.verticalScroll(rememberScrollState()).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    picture.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    picture.date,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                picture.description?.let {
                    Text(
                        it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview
@Composable
fun PictureSurfacePreview() {
    MaterialTheme {
        CompositionLocalProvider(
            LocalAsyncImagePreviewHandler provides AsyncImagePreviewHandler {
                ColorImage(Color.Red.toArgb())
            }
        ) {
            PictureSurface(
                picture = UiPictureItem(
                    title = "The Title",
                    date = "04/07/2025",
                    url = "https://picsum.photos/130/64",
                    description = "The long description"
                )
            )
        }
    }
}
