package com.dimdimbjg.gamecatalogue.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dimdimbjg.core.domain.usecase.GamesUseCase

class HomeViewModel (gamesUseCase: GamesUseCase): ViewModel() {
    val gameList = gamesUseCase.getAllGames().asLiveData()
}