package com.dimdimbjg.gamecatalogue.core.domain.repository

import com.dimdimbjg.gamecatalogue.core.data.source.Resource
import com.dimdimbjg.gamecatalogue.core.domain.model.Detail
import com.dimdimbjg.gamecatalogue.core.domain.model.Games
import kotlinx.coroutines.flow.Flow

interface IGamesRepository {

    fun getAllGames(): Flow<Resource<List<Games>>>

    fun getAllFavoritesGames(): Flow<List<Games>>

    fun getGamesDetail(id: Int): Flow<Resource<Detail>>

    suspend fun insertFavoriteGame(id: Int)

    suspend fun removeFavoriteGame(id: Int)

}