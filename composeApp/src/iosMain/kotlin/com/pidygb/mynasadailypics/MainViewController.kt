@file:Suppress("FunctionName", "unused")

package com.pidygb.mynasadailypics

import androidx.compose.ui.window.ComposeUIViewController
import com.pidygb.mynasadailypics.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}