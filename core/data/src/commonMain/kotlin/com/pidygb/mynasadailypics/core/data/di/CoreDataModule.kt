package com.pidygb.mynasadailypics.core.data.di

import com.pidygb.mynasadailypics.core.data.PicturesRepository
import com.pidygb.mynasadailypics.core.data.PicturesRepositoryImpl
import com.pidygb.mynasadailypics.core.database.di.coreDatabaseModule
import com.pidygb.mynasadailypics.core.network.di.coreNetworkModule
import org.koin.core.KoinApplication
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module


private val coreDataModule = module {
    factoryOf(::PicturesRepositoryImpl) bind PicturesRepository::class
}


fun KoinApplication.coreDataModule() {
    modules(coreDataModule)
    coreDatabaseModule()
    coreNetworkModule()
}

