package com.dimdimbjg.core.domain.usecase

import com.dimdimbjg.core.data.source.Resource
import com.dimdimbjg.core.domain.model.Detail
import com.dimdimbjg.core.domain.model.Games
import com.dimdimbjg.core.domain.repository.IGamesRepository
import kotlinx.coroutines.flow.Flow

class GamesInteractor(private val gamesRepository: IGamesRepository): GamesUseCase {
    override fun getAllGames(): Flow<Resource<List<Games>>> = gamesRepository.getAllGames()

    override fun getAllFavoritesGames(): Flow<List<Games>> = gamesRepository.getAllFavoritesGames()

    override fun getGamesDetail(id: Int): Flow<Resource<Detail>> =
        gamesRepository.getGamesDetail(id)

    override suspend fun addGameFavorites(id: Int) {
        gamesRepository.insertFavoriteGame(id)
    }

    override suspend fun removeGameFavorites(id: Int) {
        gamesRepository.removeFavoriteGame(id)
    }


}