package com.pidygb.mynasadailypics.core.database.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.pidygb.mynasadailypics.core.database.PicturesDatabase
import org.koin.core.module.Module
import org.koin.dsl.module
import java.util.*

internal actual val picturesDatabaseDriver: Module = module {
    single<SqlDriver> {
        JdbcSqliteDriver("jdbc:sqlite:pictures.db", Properties(), PicturesDatabase.Schema)
    }
}