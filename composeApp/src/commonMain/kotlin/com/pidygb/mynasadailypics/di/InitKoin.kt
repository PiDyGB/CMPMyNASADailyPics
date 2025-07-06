package com.pidygb.mynasadailypics.di

import com.pidygb.mynasadailypics.pictures.di.picturesModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        picturesModule()
    }
}