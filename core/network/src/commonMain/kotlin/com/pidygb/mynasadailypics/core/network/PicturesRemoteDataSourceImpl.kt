package com.pidygb.mynasadailypics.core.network

import com.pidygb.mynasadailypics.core.network.model.NetworkSample
import com.pidygb.mynasadailypics.core.model.Picture
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.datetime.*
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class PicturesRemoteDataSourceImpl(private val client: HttpClient) : PicturesRemoteDataSource {
    @OptIn(ExperimentalTime::class, FormatStringsInDatetimeFormats::class)
    override suspend fun getPictures(): List<Picture> = client.get("/planetary/apod") {
        parameter("start_date", LocalDate.Format {
            byUnicodePattern("yyyy-MM-dd")
        }.format(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.minus(30, DateTimeUnit.DAY)))
    }.body<List<NetworkSample>>().map {
        Picture(
            date = it.date,
            explanation = it.explanation,
            hdUrl = it.hdUrl,
            mediaType = it.mediaType,
            serviceVersion = it.serviceVersion,
            title = it.title,
            url = it.url
        )
    }
}