package com.pidygb.mynasadailypics.pictures.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pidygb.mynasadailypics.core.model.Picture
import com.pidygb.mynasadailypics.pictures.PicturesSurface
import kotlinx.serialization.Serializable

@Serializable
object Pictures

fun NavGraphBuilder.picturesScreen(onPictureClick: (picture: Picture) -> Unit) {
    composable<Pictures> {
        PicturesSurface(onPictureClick = onPictureClick)
    }
}