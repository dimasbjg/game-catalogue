package com.dimdimbjg.gamecatalogue.di

import com.dimdimbjg.gamecatalogue.core.domain.usecase.GamesInteractor
import com.dimdimbjg.gamecatalogue.core.domain.usecase.GamesUseCase
import com.dimdimbjg.gamecatalogue.detail.DetailViewModel
import com.dimdimbjg.gamecatalogue.favorites.FavoritesViewModel
import com.dimdimbjg.gamecatalogue.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GamesUseCase> { GamesInteractor(get()) }
}

val viewModelModul = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoritesViewModel(get()) }

}