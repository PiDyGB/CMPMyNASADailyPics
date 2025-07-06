package com.pidygb.mynasadailypics.core.data.sample

import com.pidygb.mynasadailypics.core.model.Sample
import kotlinx.coroutines.flow.Flow

interface SampleRepository {
    val samples: Flow<List<Sample>>
    suspend fun getSamples()
}