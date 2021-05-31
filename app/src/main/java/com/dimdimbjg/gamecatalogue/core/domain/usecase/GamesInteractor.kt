package com.dimdimbjg.gamecatalogue.core.domain.usecase

import com.dimdimbjg.gamecatalogue.core.data.source.Resource
import com.dimdimbjg.gamecatalogue.core.domain.model.Games
import com.dimdimbjg.gamecatalogue.core.domain.repository.IGamesRepository
import kotlinx.coroutines.flow.Flow

class GamesInteractor(private val gamesRepository: IGamesRepository): GamesUseCase {
    override fun getAllGames(): Flow<Resource<List<Games>>> = gamesRepository.getAllGames()

    override fun getAllFavoritesGames(): Flow<List<Games>> = gamesRepository.getAllFavoritesGames()

    override fun getGamesDetail(id: Int): Flow<Resource<Games>> =
        gamesRepository.getGamesDetail(id)

    override fun setFavoritesGames(games: Games, state: Boolean) =
        gamesRepository.setFavoritesGames(games,state)


}