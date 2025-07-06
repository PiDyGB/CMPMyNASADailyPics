package com.pidygb.mynasadailypics.pictures.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pidygb.mynasadailypics.core.common.Result
import com.pidygb.mynasadailypics.core.common.asResult
import com.pidygb.mynasadailypics.core.data.SampleRepository
import com.pidygb.mynasadailypics.pictures.model.UiPictureItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class PicturesViewModel(
    repository: SampleRepository
): ViewModel() {

    val samples = repository.samples.onStart {
        repository.getSamples()
    }.map { samples ->
        samples.map {
            UiPictureItem(
                title = it.title,
                date = it.date,
                url = it.url
            )
        }
    }.asResult().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = Result.Loading
    )
}