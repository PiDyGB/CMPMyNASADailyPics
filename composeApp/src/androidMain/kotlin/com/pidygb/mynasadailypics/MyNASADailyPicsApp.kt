package com.pidygb.mynasadailypics

import android.app.Application
import com.pidygb.mynasadailypics.di.initKoin
import org.koin.android.ext.koin.androidContext

class MyNASADailyPicsApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MyNASADailyPicsApp)
        }

    }
}