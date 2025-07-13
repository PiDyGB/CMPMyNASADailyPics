package com.pidygb.mynasadailypics.pictures

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pidygb.mynasadailypics.core.common.Result
import com.pidygb.mynasadailypics.core.ui.pictures.PictureItem
import com.pidygb.mynasadailypics.pictures.viewmodel.PicturesViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PictureSurface(
    modifier: Modifier = Modifier,
    viewModel: PicturesViewModel = koinViewModel<PicturesViewModel>(),
) {
    val result by viewModel.samples.collectAsState()
    when (val r = result) {
        is Result.Error -> Text(
            text = r.exception.message ?: "",
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )

        Result.Loading -> CircularProgressIndicator(
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )

        is Result.Success -> LazyColumn(
            contentPadding = WindowInsets.systemBars.asPaddingValues(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(r.data) { picture ->
                PictureItem(
                    title = picture.title,
                    date = picture.date,
                    url = picture.url,
                    onPictureClick = {}
                )
            }
        }
    }

}

@Preview
@Composable
fun PictureSurfacePreview() {
    MaterialTheme {
        PictureSurface()
    }
}
