package com.pidygb.mynasadailypics.core.data.sample.local

import com.pidygb.mynasadailypics.core.model.Sample
import kotlinx.coroutines.flow.Flow

interface SampleLocalDataSource {

    val samples: Flow<List<Sample>>
    fun clearAndCreateSamples(samples: List<Sample>)
}