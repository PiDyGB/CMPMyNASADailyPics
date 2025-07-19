package com.pidygb.mynasadailypics.core.database.di

import com.pidygb.mynasadailypics.core.database.PicturesDatabase
import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.module

internal expect val picturesDatabaseFactory: Module

private val picturesDatabaseModule = module {
    single { PicturesDatabase(get()) }
}


fun KoinApplication.coreDatabaseModule() {
    modules(picturesDatabaseFactory, picturesDatabaseModule)
}