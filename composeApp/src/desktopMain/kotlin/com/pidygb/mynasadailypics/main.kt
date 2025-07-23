package com.pidygb.mynasadailypics

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.pidygb.mynasadailypics.di.initKoin
import org.koin.core.logger.Level

fun main() {
    initKoin {
        printLogger(Level.DEBUG)
    }
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "mynasadailypics",
        ) {
            App()
        }
    }
}