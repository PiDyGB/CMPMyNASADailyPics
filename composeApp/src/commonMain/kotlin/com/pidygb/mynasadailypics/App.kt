package com.pidygb.mynasadailypics

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pidygb.mynasadailypics.picture.navigation.navigateToPicture
import com.pidygb.mynasadailypics.picture.navigation.pictureScreen
import com.pidygb.mynasadailypics.pictures.navigation.Pictures
import com.pidygb.mynasadailypics.pictures.navigation.picturesScreen
import org.jetbrains.compose.ui.tooling.preview.Preview


val lightColorScheme = lightColorScheme()
val darkColorScheme = darkColorScheme()

@Composable
@Preview
fun App() {
    MaterialTheme(colorScheme = if (isSystemInDarkTheme()) darkColorScheme else lightColorScheme) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Pictures) {
            picturesScreen { navController.navigateToPicture(it) }
            pictureScreen()
        }
    }
}