package com.pidygb.mynasadailypics.pictures

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pidygb.mynasadailypics.core.common.Result
import com.pidygb.mynasadailypics.core.model.Picture
import com.pidygb.mynasadailypics.core.ui.pictures.PictureItem
import com.pidygb.mynasadailypics.pictures.viewmodel.PicturesViewModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


// Test tags for UI testing
const val PICTURES_SURFACE_TEST_TAG = "pictures_surface_test_tag"
const val PICTURES_LOADING_TEST_TAG = "pictures_loading_test_tag"
const val PICTURES_ERROR_TEST_TAG = "pictures_error_test_tag"
const val PICTURES_LIST_TEST_TAG = "pictures_list_test_tag"

@Composable
fun PicturesSurface(
    modifier: Modifier = Modifier,
    viewModel: PicturesViewModel = koinViewModel<PicturesViewModel>(),
    onPictureClick: (picture: Picture) -> Unit
) {
    Surface(modifier = modifier.fillMaxSize().testTag(PICTURES_SURFACE_TEST_TAG)) {
        val result by viewModel.pictures.collectAsState()
        when (val r = result) {
            is Result.Error -> Text(
                text = r.exception.message ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .testTag(PICTURES_ERROR_TEST_TAG),
                textAlign = TextAlign.Center
            )

            Result.Loading -> CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                    .testTag(PICTURES_LOADING_TEST_TAG)
            )

            is Result.Success -> {
                val coroutineScope = rememberCoroutineScope()
                val brokenImage = rememberVectorPainter(Icons.Default.BrokenImage)
                LazyColumn(
                    modifier = Modifier.testTag(PICTURES_LIST_TEST_TAG),
                    contentPadding = WindowInsets.systemBars.asPaddingValues(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(r.data) { picture ->
                        PictureItem(
                            title = picture.title,
                            date = picture.date,
                            url = picture.url,
                            onPictureClick = {
                                coroutineScope.launch {
                                    viewModel.pictureByDate(it)?.let(onPictureClick)
                                }
                            },
                            imageError = brokenImage
                        )
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun PicturesSurfacePreview() {
    MaterialTheme {
        PicturesSurface {}
    }
}
