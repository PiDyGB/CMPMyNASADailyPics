package com.pidygb.mynasadailypics.core.network

import com.pidygb.mynasadailypics.core.network.model.NetworkSample
import io.ktor.client.engine.mock.*
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class PicturesRemoteDataSourceImplTest {

    private val mockData = listOf(
        NetworkSample(
            date = "2023-01-01",
            explanation = "explanation1",
            hdUrl = "hdurl1",
            mediaType = "image",
            serviceVersion = "v1",
            title = "title1",
            url = "url1"
        ),
        NetworkSample(
            date = "2023-01-02",
            explanation = "explanation2",
            hdUrl = "hdurl2",
            mediaType = "image",
            serviceVersion = "v1",
            title = "title2",
            url = "url2"
        )
    )

    @Test
    fun `getPictures returns pictures when the call is successful`() = runTest {
        val mockEngine = MockEngine {
            respond(
                content = Json.encodeToString(mockData),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val client = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
        val dataSource = PicturesRemoteDataSourceImpl(client)
        val pictures = dataSource.getPictures()
        assertEquals(2, pictures.size)
        assertEquals("title1", pictures[0].title)
    }

    @Test
    fun `getPictures throws an exception when the call is unsuccessful`() = runTest {
        val mockEngine = MockEngine {
            respondError(HttpStatusCode.InternalServerError)
        }
        val client = HttpClient(mockEngine)
        val dataSource = PicturesRemoteDataSourceImpl(client)
        assertFails {
            dataSource.getPictures()
        }
    }
}
