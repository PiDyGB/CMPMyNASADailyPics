package com.pidygb.mynasadailypics.core.network.di

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
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
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.v(message, null, "HTTP Client")
                    }
                }
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
        }.also { Napier.base(DebugAntilog()) }
    }
}

fun KoinApplication.coreNetworkModule() {
    modules(coreNetworkModule)
}