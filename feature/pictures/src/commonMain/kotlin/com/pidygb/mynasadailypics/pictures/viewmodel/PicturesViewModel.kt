package com.pidygb.mynasadailypics.pictures.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pidygb.mynasadailypics.core.common.Result
import com.pidygb.mynasadailypics.core.common.asResult
import com.pidygb.mynasadailypics.core.data.PicturesRepository
import com.pidygb.mynasadailypics.core.model.Picture
import com.pidygb.mynasadailypics.pictures.model.UiPictureItem
import kotlinx.coroutines.flow.*

class PicturesViewModel(
    private val repository: PicturesRepository
) : ViewModel() {

    val samples = repository.pictures.onStart {
        repository.getPictures()
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


    suspend fun pictureByDate(date: String): Picture? = repository.pictures.map {
        it.firstOrNull { picture -> picture.date == date }
    }.firstOrNull()
}