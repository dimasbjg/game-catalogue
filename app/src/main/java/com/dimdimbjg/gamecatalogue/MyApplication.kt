package com.dimdimbjg.gamecatalogue

import android.app.Application
import com.dimdimbjg.core.di.networkModule
import com.dimdimbjg.core.di.repositoryModule
import com.dimdimbjg.core.di.databaseModule
import com.dimdimbjg.gamecatalogue.di.useCaseModule
import com.dimdimbjg.gamecatalogue.di.viewModelModule
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
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}