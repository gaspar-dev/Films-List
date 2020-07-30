package com.am.films_list

import android.app.Application
import com.am.films_list.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDi()
    }

    private fun initDi() {
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}