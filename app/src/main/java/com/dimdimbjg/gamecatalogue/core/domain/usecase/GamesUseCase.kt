package com.dimdimbjg.gamecatalogue.core.domain.usecase

import com.dimdimbjg.gamecatalogue.core.data.source.Resource
import com.dimdimbjg.gamecatalogue.core.domain.model.Games
import kotlinx.coroutines.flow.Flow

interface GamesUseCase {
    fun getAllGames(): Flow<Resource<List<Games>>>

    fun getAllFavoritesGames(): Flow<List<Games>>

    fun getGamesDetail(id: Int): Flow<Resource<Games>>

    fun setFavoritesGames(games: Games, state: Boolean)


}