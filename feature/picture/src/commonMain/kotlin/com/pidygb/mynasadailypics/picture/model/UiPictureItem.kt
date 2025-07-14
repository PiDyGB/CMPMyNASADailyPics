package com.pidygb.mynasadailypics.picture.model

import kotlinx.serialization.Serializable

@Serializable
data class UiPictureItem(
    val title: String,
    val date: String,
    val url: String?,
    val description: String?
)
