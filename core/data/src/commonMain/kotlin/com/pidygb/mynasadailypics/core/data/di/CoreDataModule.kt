package com.pidygb.mynasadailypics.core.data.di

import com.pidygb.mynasadailypics.core.data.SampleRepository
import com.pidygb.mynasadailypics.core.data.SampleRepositoryImpl
import com.pidygb.mynasadailypics.core.datastore.di.coreDataStoreModule
import com.pidygb.mynasadailypics.core.network.di.coreNetworkModule
import org.koin.core.KoinApplication
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module


private val coreDataModule = module {
    factoryOf(::SampleRepositoryImpl) bind SampleRepository::class
}


fun KoinApplication.coreDataModule() {
    modules(coreDataModule)
    coreDataStoreModule()
    coreNetworkModule()
}

