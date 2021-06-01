package com.dimdimbjg.gamecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dimdimbjg.core.domain.usecase.GamesUseCase
import com.dimdimbjg.gamecatalogue.di.viewModelModule
import kotlinx.coroutines.launch

class DetailViewModel constructor(private val gamesUseCase: GamesUseCase) : ViewModel() {


    fun gameDetail(id: Int) = gamesUseCase.getGamesDetail(id).asLiveData()

    fun checkIsFavorite(id: Int): LiveData<Boolean> = gamesUseCase.checkIsFavorites(id)

    fun addGameToFavorites(id: Int) {
        viewModelScope.launch {
            gamesUseCase.addGameFavorites(id)
        }
    }

    fun removeGameFromFavorite(id: Int) {
        viewModelScope.launch {
            gamesUseCase.removeGameFavorites(id)
        }
    }

}



