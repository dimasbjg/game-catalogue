package com.dimdimbjg.gamecatalogue.di

import com.dimdimbjg.core.domain.usecase.GamesInteractor
import com.dimdimbjg.core.domain.usecase.GamesUseCase
import com.dimdimbjg.gamecatalogue.ui.detail.DetailViewModel
import com.dimdimbjg.gamecatalogue.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GamesUseCase> { GamesInteractor(get()) }
}

val viewModelModul = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }

}