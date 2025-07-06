package com.pidygb.mynasadailypics.core.data.di

import com.pidygb.mynasadailypics.core.data.sample.SampleRepository
import com.pidygb.mynasadailypics.core.data.sample.SampleRepositoryImpl
import com.pidygb.mynasadailypics.core.data.sample.local.SampleLocalDataSource
import com.pidygb.mynasadailypics.core.data.sample.local.SampleLocalDataSourceImpl
import com.pidygb.mynasadailypics.core.data.sample.remote.SampleRemoteDataSource
import com.pidygb.mynasadailypics.core.data.sample.remote.SampleRemoteDataSourceImpl
import com.pidygb.mynasadailypics.core.datastore.di.coreDatabaseModule
import com.pidygb.mynasadailypics.core.network.di.coreNetworkModule
import org.koin.core.KoinApplication
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module


private val coreDataModule = module {
    factoryOf(::SampleRemoteDataSourceImpl) bind SampleRemoteDataSource::class
    factoryOf(::SampleLocalDataSourceImpl) bind SampleLocalDataSource::class
    factoryOf(::SampleRepositoryImpl) bind SampleRepository::class
}


fun KoinApplication.coreDataModule() {
    modules(coreDataModule)
    coreDatabaseModule()
    coreNetworkModule()
}

