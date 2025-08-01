package com.pidygb.mynasadailypics.core.network.di

import com.pidygb.mynasadailypics.core.network.PicturesRemoteDataSource
import com.pidygb.mynasadailypics.core.network.PicturesRemoteDataSourceImpl
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

private val httpClientModule = module {
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
    factoryOf(::PicturesRemoteDataSourceImpl) bind PicturesRemoteDataSource::class
}

fun Module.includesCoreNetworkModule() {
    includes(httpClientModule)
}