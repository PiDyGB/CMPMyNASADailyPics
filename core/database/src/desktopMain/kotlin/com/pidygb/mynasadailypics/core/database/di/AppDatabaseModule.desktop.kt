package com.pidygb.mynasadailypics.core.database.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.pidygb.mynasadailypics.core.database.AppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module
import java.util.Properties

internal actual val databaseFactory: Module = module {
    single<SqlDriver> {
        JdbcSqliteDriver("jdbc:sqlite:sample.db", Properties(), AppDatabase.Schema)
    }
}