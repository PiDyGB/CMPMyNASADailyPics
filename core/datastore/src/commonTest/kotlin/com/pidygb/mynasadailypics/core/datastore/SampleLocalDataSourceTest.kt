package com.pidygb.mynasadailypics.core.datastore

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import app.cash.turbine.test
import com.pidygb.mynasadailypics.core.database.AppDatabase
import com.pidygb.mynasadailypics.core.database.AppDatabaseQueries
import com.pidygb.mynasadailypics.core.model.Sample
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class SampleLocalDataSourceTest : KoinTest {

    private val sampleLocalDataSource: SampleLocalDataSource by inject()
    private val queries: AppDatabaseQueries by inject()

    @BeforeTest
    fun setup() {
        startKoin {
            modules(
                module {
                    single<SqlDriver> {
                        JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).apply {
                            AppDatabase.Schema.create(this)
                        }
                    }
                    single { AppDatabase(get()).appDatabaseQueries }
                    single<SampleLocalDataSource> { SampleLocalDataSourceImpl(get()) }
                }
            )
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `test clearAndCreateSamples inserts new samples`() = runTest {
        val samples = listOf(
            Sample("2023-01-01", "Explanation 1", "hdurl1", "image", "v1", "Title 1", "url1"),
            Sample("2023-01-02", "Explanation 2", "hdurl2", "image", "v1", "Title 2", "url2")
        )

        sampleLocalDataSource.clearAndCreateSamples(samples)

        val dbSamples = queries.selectAllSamples().executeAsList()
        assertEquals(2, dbSamples.size)
        assertEquals("2023-01-01", dbSamples[0].date)
        assertEquals("2023-01-02", dbSamples[1].date)
    }

    @Test
    fun `test samples flow emits correct data`() = runTest {
        val samples = listOf(
            Sample("2023-01-01", "Explanation 1", "hdurl1", "image", "v1", "Title 1", "url1"),
            Sample("2023-01-02", "Explanation 2", "hdurl2", "image", "v1", "Title 2", "url2")
        )

        sampleLocalDataSource.samples.test {
            assertEquals(emptyList(), awaitItem()) // Initial empty list

            sampleLocalDataSource.clearAndCreateSamples(samples)
            val emittedSamples = awaitItem()
            assertEquals(2, emittedSamples.size)
            assertEquals("2023-01-01", emittedSamples[0].date)
            assertEquals("2023-01-02", emittedSamples[1].date)
        }
    }

    @Test
    fun `test clearAndCreateSamples clears existing samples`() = runTest {
        val initialSamples = listOf(
            Sample("2023-01-01", "Explanation 1", "hdurl1", "image", "v1", "Title 1", "url1")
        )
        sampleLocalDataSource.clearAndCreateSamples(initialSamples)

        val newSamples = listOf(
            Sample("2023-01-02", "Explanation 2", "hdurl2", "image", "v1", "Title 2", "url2")
        )
        sampleLocalDataSource.clearAndCreateSamples(newSamples)

        val dbSamples = queries.selectAllSamples().executeAsList()
        assertEquals(1, dbSamples.size)
        assertEquals("2023-01-02", dbSamples[0].date)
    }
}
