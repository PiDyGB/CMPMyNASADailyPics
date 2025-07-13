package com.pidygb.mynasadailypics.core.ui.pictures

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PictureItem(
    modifier: Modifier = Modifier,
    title: String,
    date: String,
    url: String,
    onPictureClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable(onClick = onPictureClick)
            .padding(horizontal = 16.dp)
    ) {
        Column(Modifier.weight(1f).fillMaxHeight()) {
            Text(title, modifier = Modifier.fillMaxWidth())
            Text(date, modifier = Modifier.fillMaxWidth())
        }
        AsyncImage(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(16f / 9f),
            model = url,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun PictureSurfacePreview() {
    MaterialTheme {
        PictureItem(
            title = "habitant",
            date = "graecis",
            url = "https://picsum.photos/130/64",
            onPictureClick = {})
    }
}


