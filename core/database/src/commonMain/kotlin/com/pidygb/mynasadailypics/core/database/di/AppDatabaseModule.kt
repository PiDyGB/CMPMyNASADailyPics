package com.pidygb.mynasadailypics.core.database.di

import com.pidygb.mynasadailypics.core.database.SamplesDatabase
import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.module

internal expect val samplesDatabaseFactory: Module

private val samplesDatabaseModule = module {
    single { SamplesDatabase(get()).samplesDatabaseQueries }
}


fun KoinApplication.coreDatabaseModule() {
    modules(samplesDatabaseFactory, samplesDatabaseModule)
}