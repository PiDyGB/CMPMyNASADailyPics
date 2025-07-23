package com.pidygb.mynasadailypics

import android.app.Application
import com.pidygb.mynasadailypics.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class MyNASADailyPicsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyNASADailyPicsApp)
        }

    }
}