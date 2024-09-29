package com.example.newsappx

import android.app.Application
import com.example.newsappx.di.modules.appModule
import com.example.newsappx.di.modules.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            AndroidLogger(Level.INFO)
            androidContext(this@App)
            modules(appModule, dataModule)
        }
    }
}