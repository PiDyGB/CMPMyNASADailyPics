package com.pidygb.mynasadailypics

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.pidygb.mynasadailypics.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "mynasadailypics",
        ) {
            App()
        }
    }
}