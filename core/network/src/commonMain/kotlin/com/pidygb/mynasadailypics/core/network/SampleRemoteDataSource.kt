package com.pidygb.mynasadailypics.core.network

import com.pidygb.mynasadailypics.core.model.Picture

interface SampleRemoteDataSource {
    suspend fun getSamples(): List<Picture>
}