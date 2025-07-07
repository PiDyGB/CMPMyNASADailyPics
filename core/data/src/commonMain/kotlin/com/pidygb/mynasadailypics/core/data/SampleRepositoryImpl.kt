package com.pidygb.mynasadailypics.core.data

import com.pidygb.mynasadailypics.core.database.SamplesDatabaseQueries
import com.pidygb.mynasadailypics.core.database.resetAllSamplesEntities
import com.pidygb.mynasadailypics.core.database.selectAllSamples
import com.pidygb.mynasadailypics.core.model.Sample
import com.pidygb.mynasadailypics.core.network.SampleRemoteDataSource
import kotlinx.coroutines.flow.Flow

class SampleRepositoryImpl(
    private val queries: SamplesDatabaseQueries,
    private val remoteDataSource: SampleRemoteDataSource
) : SampleRepository {
    override val samples: Flow<List<Sample>> = queries.selectAllSamples()

    override suspend fun getSamples() {
        queries.resetAllSamplesEntities(remoteDataSource.getSamples())
    }

}