package com.pidygb.mynasadailypics.core.database.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.pidygb.mynasadailypics.core.database.SamplesDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val samplesDatabaseFactory: Module = module {
    single<SqlDriver> {
        NativeSqliteDriver(SamplesDatabase.Schema, "launch.db")
    }
}