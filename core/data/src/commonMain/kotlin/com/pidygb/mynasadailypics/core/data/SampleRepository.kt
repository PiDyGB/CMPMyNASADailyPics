package com.pidygb.mynasadailypics.core.data

import com.pidygb.mynasadailypics.core.model.Picture
import kotlinx.coroutines.flow.Flow

interface SampleRepository {
    val samples: Flow<List<Picture>>
    suspend fun getSamples()
}