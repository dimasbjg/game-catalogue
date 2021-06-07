package com.dimdimbjg.core.di

import com.dimdimbjg.core.data.source.GamesRepository
import com.dimdimbjg.core.data.source.local.LocalDataSource
import com.dimdimbjg.core.data.source.remote.RemoteDataSource
import com.dimdimbjg.core.domain.repository.IGamesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IGamesRepository> { GamesRepository(get(), get()) }
}