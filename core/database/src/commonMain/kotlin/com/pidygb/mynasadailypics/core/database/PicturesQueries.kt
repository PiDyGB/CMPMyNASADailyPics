package com.pidygb.mynasadailypics.core.database

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.pidygb.mynasadailypics.core.model.Picture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

fun PicturesDatabaseQueries.selectAllPictures(): Flow<List<Picture>> =
    selectAllPicturesEntities { date, explanation, hdUrl, mediaType, serviceVersion, title, url ->
        Picture(
            date = date,
            explanation = explanation,
            hdUrl = hdUrl,
            mediaType = mediaType,
            serviceVersion = serviceVersion,
            title = title,
            url = url
        )
    }.asFlow().mapToList(Dispatchers.IO)

fun PicturesDatabaseQueries.resetAllPicturesEntities(pictures: List<Picture>) {
    transaction {
        deleteAllPicturesEntities()
        pictures.forEach { sample ->
            insertPictureEntity(
                date = sample.date,
                explanation = sample.explanation,
                hdUrl = sample.hdUrl,
                mediaType = sample.mediaType,
                serviceVersion = sample.serviceVersion,
                title = sample.title,
                url = sample.url
            )
        }
    }
}