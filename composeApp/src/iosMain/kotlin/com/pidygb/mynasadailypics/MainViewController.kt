@file:Suppress("FunctionName", "unused")

package com.pidygb.mynasadailypics

import androidx.compose.ui.window.ComposeUIViewController
import com.pidygb.mynasadailypics.di.initKoin
import org.koin.core.logger.Level

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin {
            printLogger(Level.DEBUG)
        }
    }
) {
    App()
}