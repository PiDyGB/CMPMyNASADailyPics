package com.pidygb.mynasadailypics.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.inMemoryDriver

internal actual fun createDriver(): SqlDriver = inMemoryDriver(SamplesDatabase.Schema)