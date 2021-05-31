package com.dimdimbjg.gamecatalogue.core.di

import com.dimdimbjg.gamecatalogue.core.data.source.GamesRepository
import com.dimdimbjg.gamecatalogue.core.data.source.local.LocalDataSource
import com.dimdimbjg.gamecatalogue.core.data.source.remote.RemoteDataSource
import com.dimdimbjg.gamecatalogue.core.domain.repository.IGamesRepository
import com.dimdimbjg.gamecatalogue.core.utils.AppExecutors
import org.koin.dsl.module

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IGamesRepository> { GamesRepository(get(), get(), get()) }
}