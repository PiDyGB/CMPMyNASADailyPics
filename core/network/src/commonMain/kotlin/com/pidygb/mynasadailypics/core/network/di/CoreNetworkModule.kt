package com.pidygb.mynasadailypics.core.network.di

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.koin.dsl.module

private val coreNetworkModule = module {
    single {
        HttpClient {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true })
            }
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "api.nasa.gov"
                    parameters.append("api_key", "YcqaqQFhuLhWXiR13kXxmGvoTmx0iT4NTuPNAOyd")
                }
                contentType(ContentType.Application.Json)
            }
        }
    }
}

fun KoinApplication.coreNetworkModule() {
    modules(coreNetworkModule)
}