package com.pidygb.mynasadailypics

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pidygb.mynasadailypics.pictures.PictureSurface
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Box(Modifier.fillMaxSize()) {
            PictureSurface()
        }
    }
}