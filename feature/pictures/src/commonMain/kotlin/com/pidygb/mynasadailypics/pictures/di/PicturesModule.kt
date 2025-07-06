package com.pidygb.mynasadailypics.pictures.di

import com.pidygb.mynasadailypics.core.data.di.coreDataModule
import com.pidygb.mynasadailypics.pictures.viewmodel.PicturesViewModel
import org.koin.core.KoinApplication
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


private val picturesModule = module {
    viewModelOf(::PicturesViewModel)
}


fun KoinApplication.picturesModule() {
    modules(picturesModule)
    coreDataModule()
}
