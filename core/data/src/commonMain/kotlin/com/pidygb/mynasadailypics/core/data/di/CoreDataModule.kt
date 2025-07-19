package com.pidygb.mynasadailypics.core.data.di

import com.pidygb.mynasadailypics.core.data.PicturesRepository
import com.pidygb.mynasadailypics.core.data.PicturesRepositoryImpl
import com.pidygb.mynasadailypics.core.database.di.coreDatabaseModule
import com.pidygb.mynasadailypics.core.network.di.coreNetworkModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.KoinApplication
import org.koin.dsl.bind
import org.koin.dsl.module


private val coreDataModule = module {
    factory {
        PicturesRepositoryImpl(
            database = get(),
            remoteDataSource = get(),
            context = Dispatchers.IO
        )
    } bind PicturesRepository::class
}


fun KoinApplication.coreDataModule() {
    modules(coreDataModule)
    coreDatabaseModule()
    coreNetworkModule()
}

