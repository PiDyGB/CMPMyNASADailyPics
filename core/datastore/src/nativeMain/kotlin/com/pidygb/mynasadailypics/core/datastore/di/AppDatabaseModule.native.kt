package com.pidygb.mynasadailypics.core.datastore.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.pidygb.mynasadailypics.core.datastore.AppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val databaseFactory: Module = module {
    single<SqlDriver> {
        NativeSqliteDriver(AppDatabase.Schema, "launch.db")
    }
}