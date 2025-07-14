package com.pidygb.mynasadailypics.picture

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import coil3.compose.SubcomposeAsyncImage
import com.pidygb.mynasadailypics.picture.model.UiPictureItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PictureSurface(
    modifier: Modifier = Modifier,
    picture: UiPictureItem
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Column {
            SubcomposeAsyncImage(
                model = picture.url,
                contentDescription = "A random image from Picsum Photos",
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth().fillMaxHeight(1 / 2f)
                            .wrapContentSize(Alignment.Center)
                    )
                },
                error = {
                    Icon(
                        imageVector = Icons.Default.BrokenImage,
                        contentDescription = "Image failed to load"
                    )
                },
                modifier = Modifier.fillMaxWidth().fillMaxHeight(1 / 2f),
            )
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
                    url = "https://search.yahoo.com/search?p=neglegentur",
                    description = "The long description"
                )
            )
        }
    }
}
