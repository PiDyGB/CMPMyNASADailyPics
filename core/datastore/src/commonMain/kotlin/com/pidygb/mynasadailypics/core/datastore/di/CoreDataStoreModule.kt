package com.pidygb.mynasadailypics.core.datastore.di

import com.pidygb.mynasadailypics.core.database.di.coreDatabaseModule
import com.pidygb.mynasadailypics.core.datastore.SampleLocalDataSource
import com.pidygb.mynasadailypics.core.datastore.SampleLocalDataSourceImpl
import org.koin.core.KoinApplication
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module


private val coreDataStoreModule = module {
    factoryOf(::SampleLocalDataSourceImpl) bind SampleLocalDataSource::class
}


fun KoinApplication.coreDataStoreModule() {
    modules(coreDataStoreModule)
    coreDatabaseModule()
}

