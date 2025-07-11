package com.pidygb.mynasadailypics.core.database

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.pidygb.mynasadailypics.core.model.Sample
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

fun SamplesDatabaseQueries.selectAllSamples(): Flow<List<Sample>> =
    selectAllSamplesEntities { date, explanation, hdUrl, mediaType, serviceVersion, title, url ->
        Sample(
            date = date,
            explanation = explanation,
            hdUrl = hdUrl,
            mediaType = mediaType,
            serviceVersion = serviceVersion,
            title = title,
            url = url
        )
    }.asFlow().mapToList(Dispatchers.IO)

fun SamplesDatabaseQueries.resetAllSamplesEntities(samples: List<Sample>) {
    transaction {
        deleteAllSamplesEntities()
        samples.forEach { sample ->
            insertSampleEntity(
                date = sample.date,
                explanation = sample.explanation,
                hdUrl = sample.hdUrl,
                mediaType = sample.mediaType,
                serviceVersion = sample.serviceVersion,
                title = sample.title,
                url = sample.url
            )
        }
    }
}