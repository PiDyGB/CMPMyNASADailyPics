package com.pidygb.mynasadailypics.core.database.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.pidygb.mynasadailypics.core.database.SamplesDatabase
import org.koin.core.module.Module
import org.koin.dsl.module
import java.util.*

internal actual val samplesDatabaseFactory: Module = module {
    single<SqlDriver> {
        JdbcSqliteDriver("jdbc:sqlite:sample.db", Properties(), SamplesDatabase.Schema)
    }
}