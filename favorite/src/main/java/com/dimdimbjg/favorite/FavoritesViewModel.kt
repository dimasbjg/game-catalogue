package com.dimdimbjg.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dimdimbjg.core.domain.usecase.GamesUseCase

class FavoritesViewModel(gamesUseCase: GamesUseCase): ViewModel() {

    val listFavorites = gamesUseCase.getAllFavoritesGames().asLiveData()

}