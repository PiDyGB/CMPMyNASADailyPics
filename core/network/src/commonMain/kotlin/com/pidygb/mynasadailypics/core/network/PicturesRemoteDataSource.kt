package com.pidygb.mynasadailypics.core.network

import com.pidygb.mynasadailypics.core.model.Picture

interface PicturesRemoteDataSource {
    suspend fun getPictures(): List<Picture>
}