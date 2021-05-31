package com.dimdimbjg.gamecatalogue

import android.app.Application
import com.dimdimbjg.gamecatalogue.core.di.networkModule
import com.dimdimbjg.gamecatalogue.core.di.repositoryModule
import com.dimdimbjg.gamecatalogue.core.di.storageModule
import com.dimdimbjg.gamecatalogue.di.useCaseModule
import com.dimdimbjg.gamecatalogue.di.viewModelModul
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    storageModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModul
                )
            )
        }
    }
}