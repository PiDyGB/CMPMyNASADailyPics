package com.pidygb.mynasadailypics.core.data.sample

import com.pidygb.mynasadailypics.core.data.sample.local.SampleLocalDataSource
import com.pidygb.mynasadailypics.core.data.sample.remote.SampleRemoteDataSource
import com.pidygb.mynasadailypics.core.model.Sample
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