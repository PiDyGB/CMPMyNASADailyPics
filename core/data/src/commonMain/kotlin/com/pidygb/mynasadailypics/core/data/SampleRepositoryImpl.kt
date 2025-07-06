package com.pidygb.mynasadailypics.core.data

import com.pidygb.mynasadailypics.core.datastore.SampleLocalDataSource
import com.pidygb.mynasadailypics.core.model.Sample
import com.pidygb.mynasadailypics.core.network.SampleRemoteDataSource
import kotlinx.coroutines.flow.Flow

class SampleRepositoryImpl(
    private val localDataSource: SampleLocalDataSource,
    private val remoteDataSource: SampleRemoteDataSource
): SampleRepository {
    override val samples: Flow<List<Sample>> = localDataSource.samples

    override suspend fun getSamples() {
        val samples = remoteDataSource.getSamples()
        localDataSource.clearAndCreateSamples(samples)
    }

}