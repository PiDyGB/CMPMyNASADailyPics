package com.pidygb.mynasadailypics.core.network

import com.pidygb.mynasadailypics.core.network.model.NetworkSample
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class PicturesRemoteDataSourceImplTest {

    @Test
    fun `getPictures returns a list of pictures`() = runTest {
        val mockEngine = MockEngine {
            respond(
                content = Json.encodeToString(listOf(
                    NetworkSample(
                        date = "2023-01-01",
                        explanation = "Explanation 1",
                        hdUrl = "hdurl1.com",
                        mediaType = "image",
                        serviceVersion = "v1",
                        title = "Title 1",
                        url = "url1.com"
                    )
                )),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json()
            }
        }

        val dataSource = PicturesRemoteDataSourceImpl(client)
        val pictures = dataSource.getPictures()

        assertEquals(1, pictures.size)
        assertEquals("Title 1", pictures[0].title)
    }
}
