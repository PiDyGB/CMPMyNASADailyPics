package com.pidygb.mynasadailypics.core.data.sample.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.pidygb.mynasadailypics.core.datastore.AppDatabaseQueries
import com.pidygb.mynasadailypics.core.model.Sample
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.map

class SampleLocalDataSourceImpl(private val queries: AppDatabaseQueries) : SampleLocalDataSource {

    override val samples = queries.selectAllSamples().asFlow().mapToList(Dispatchers.IO).map { samples ->
        samples.map { sample ->
            Sample(
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

    override fun clearAndCreateSamples(samples: List<Sample>) {
        queries.transaction {
            queries.deleteAllSamples()
            samples.forEach { sample ->
                queries.insertSample(
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
}