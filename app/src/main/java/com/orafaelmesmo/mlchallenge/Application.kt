package com.orafaelmesmo.mlchallenge

import android.app.Application
import com.orafaelmesmo.mlchallenge.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(
                appModule,
            )
        }
    }
}
