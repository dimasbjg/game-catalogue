package com.dimdimbjg.favorite.di

import com.dimdimbjg.favorite.FavoritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoritesViewModel(get()) }
}