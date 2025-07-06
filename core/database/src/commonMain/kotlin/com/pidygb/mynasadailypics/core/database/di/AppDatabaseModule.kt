package com.pidygb.mynasadailypics.core.database.di

import com.pidygb.mynasadailypics.core.database.AppDatabase
import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.module

internal expect val databaseFactory: Module

private val database = module {
    single { AppDatabase(get()).appDatabaseQueries }
}


fun KoinApplication.coreDatabaseModule() {
    modules(databaseFactory, database)
}