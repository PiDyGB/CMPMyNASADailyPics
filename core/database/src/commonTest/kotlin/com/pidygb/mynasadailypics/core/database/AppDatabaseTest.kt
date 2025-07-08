package com.pidygb.mynasadailypics.core.database

import app.cash.sqldelight.db.SqlDriver
import kotlin.test.*

internal expect fun createDriver(): SqlDriver

class AppDatabaseTest {

    private lateinit var driver: SqlDriver
    private lateinit var database: SamplesDatabase
    private lateinit var queries: SamplesDatabaseQueries

    @BeforeTest
    fun setup() {
        driver = createDriver()
        database = SamplesDatabase(driver)
        queries = database.samplesDatabaseQueries
    }

    @AfterTest
    fun tearDown() {
        driver.close()
    }

    @Test
    fun `insertSample and selectAllSamples work correctly`() {
        assertTrue(queries.selectAllSamplesEntities().executeAsList().isEmpty())

        queries.insertSampleEntity(
            date = "2023-01-01",
            explanation = "Explanation 1",
            hdUrl = "hdurl1.com",
            mediaType = "image",
            serviceVersion = "v1",
            title = "Title 1",
            url = "url1.com"
        )

        var samples = queries.selectAllSamplesEntities().executeAsList()
        assertEquals(1, samples.size)
        assertEquals("2023-01-01", samples[0].date)
        assertEquals("Title 1", samples[0].title)

        queries.insertSampleEntity(
            date = "2023-01-02",
            explanation = "Explanation 2",
            hdUrl = "hdurl2.com",
            mediaType = "image",
            serviceVersion = "v1",
            title = "Title 2",
            url = "url2.com"
        )

        samples = queries.selectAllSamplesEntities().executeAsList()
        assertEquals(2, samples.size)
        // Samples should be ordered by date descending
        assertEquals("2023-01-02", samples[0].date)
        assertEquals("2023-01-01", samples[1].date)
    }

    @Test
    fun `insertSample replaces existing sample with same date`() {
        queries.insertSampleEntity(
            date = "2023-01-01",
            explanation = "Initial Explanation",
            hdUrl = "initialhdurl.com",
            mediaType = "image",
            serviceVersion = "v1",
            title = "Initial Title",
            url = "initialurl.com"
        )

        var samples = queries.selectAllSamplesEntities().executeAsList()
        assertEquals(1, samples.size)
        assertEquals("Initial Title", samples[0].title)

        queries.insertSampleEntity(
            date = "2023-01-01",
            explanation = "Updated Explanation",
            hdUrl = "updatedhdurl.com",
            mediaType = "image",
            serviceVersion = "v1",
            title = "Updated Title",
            url = "updatedurl.com"
        )

        samples = queries.selectAllSamplesEntities().executeAsList()
        assertEquals(1, samples.size)
        assertEquals("Updated Title", samples[0].title)
        assertEquals("Updated Explanation", samples[0].explanation)
    }

    @Test
    fun `deleteAllSamples removes all samples`() {
        queries.insertSampleEntity("2023-01-01", "E1", "h1", "image", "v1", "T1", "u1")
        queries.insertSampleEntity("2023-01-02", "E2", "h2", "image", "v1", "T2", "u2")

        var samples = queries.selectAllSamplesEntities().executeAsList()
        assertEquals(2, samples.size)

        queries.deleteAllSamplesEntities()
        samples = queries.selectAllSamplesEntities().executeAsList()
        assertTrue(samples.isEmpty())
    }
}
