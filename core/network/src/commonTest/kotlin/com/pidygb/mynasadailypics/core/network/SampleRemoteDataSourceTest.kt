package com.pidygb.mynasadailypics.core.network

import com.pidygb.mynasadailypics.core.network.model.NetworkSample
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
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
class SampleRemoteDataSourceTest : KoinTest {

    private val sampleRemoteDataSource: SampleRemoteDataSource by inject()
    private lateinit var mockEngine: MockEngine

    @BeforeTest
    fun setup() {
        mockEngine = MockEngine { request ->
            val responseHeaders = headersOf(HttpHeaders.ContentType, "application/json")
            val samples = listOf(
                NetworkSample("2023-01-01", "Explanation 1", "hdurl1", "image", "v1", "Title 1", "url1"),
                NetworkSample("2023-01-02", "Explanation 2", "hdurl2", "image", "v1", "Title 2", "url2")
            )
            val json = Json.encodeToString(samples)
            respond(json, HttpStatusCode.OK, responseHeaders)
        }

        startKoin {
            modules(
                module {
                    single {
                        HttpClient(mockEngine) {
                            install(ContentNegotiation) {
                                json(Json {
                                    ignoreUnknownKeys = true
                                    prettyPrint = true
                                    isLenient = true
                                })
                            }
                        }
                    }
                    single<SampleRemoteDataSource> { SampleRemoteDataSourceImpl(get()) }
                }
            )
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `test getSamples returns correct data`() = runTest {
        val samples = sampleRemoteDataSource.getSamples()

        assertEquals(2, samples.size)
        assertEquals("2023-01-01", samples[0].date)
        assertEquals("Title 1", samples[0].title)
        assertEquals("2023-01-02", samples[1].date)
        assertEquals("Title 2", samples[1].title)
    }

    @Test
    fun `test getSamples handles API error`() = runTest {
        mockEngine.config.requestHandlers = listOf {
            respondError(HttpStatusCode.InternalServerError)
        }

        try {
            sampleRemoteDataSource.getSamples()
            // Should throw an exception
            assert(false) { "API error was not propagated" }
        } catch (e: Exception) {
            // Expected exception
            assert(true)
        }
    }
}
