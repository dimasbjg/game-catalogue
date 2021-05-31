package com.dimdimbjg.gamecatalogue.core.di

import androidx.room.Room
import com.dimdimbjg.gamecatalogue.core.data.source.local.room.GamesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val storageModule = module {
    factory { get<GamesDatabase>().gamesDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            GamesDatabase::class.java,"games.db"
        ).fallbackToDestructiveMigration()
    }
}