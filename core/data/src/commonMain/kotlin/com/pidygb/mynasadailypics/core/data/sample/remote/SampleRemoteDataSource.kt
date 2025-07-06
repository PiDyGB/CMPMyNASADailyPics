package com.pidygb.mynasadailypics.core.data.sample.remote

import com.pidygb.mynasadailypics.core.model.Sample

interface SampleRemoteDataSource {
    suspend fun getSamples(): List<Sample>
}