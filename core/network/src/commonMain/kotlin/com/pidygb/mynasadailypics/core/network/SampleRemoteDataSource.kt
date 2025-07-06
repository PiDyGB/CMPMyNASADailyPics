package com.pidygb.mynasadailypics.core.network

import com.pidygb.mynasadailypics.core.model.Sample

interface SampleRemoteDataSource {
    suspend fun getSamples(): List<Sample>
}