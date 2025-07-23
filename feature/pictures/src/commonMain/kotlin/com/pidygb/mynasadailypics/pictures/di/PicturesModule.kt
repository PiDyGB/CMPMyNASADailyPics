package com.pidygb.mynasadailypics.pictures.di

import com.pidygb.mynasadailypics.core.data.di.includesCoreDataModule
import com.pidygb.mynasadailypics.pictures.viewmodel.PicturesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val picturesModule = module {
    includesCoreDataModule()
    viewModelOf(::PicturesViewModel)
}

