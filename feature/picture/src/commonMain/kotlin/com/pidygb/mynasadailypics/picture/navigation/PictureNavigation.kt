package com.pidygb.mynasadailypics.picture.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.pidygb.mynasadailypics.core.model.Picture
import com.pidygb.mynasadailypics.picture.PictureSurface
import com.pidygb.mynasadailypics.picture.model.UiPictureItem

fun NavController.navigateToPicture(picture: Picture) {
    navigate(
        UiPictureItem(
            title = picture.title, date = picture.date, url = picture.hdUrl, description = picture.explanation
        )
    )
}

fun NavGraphBuilder.pictureScreen() {
    composable<UiPictureItem> {
        PictureSurface(picture = it.toRoute())
    }
}