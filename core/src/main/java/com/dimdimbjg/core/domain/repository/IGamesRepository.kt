package com.dimdimbjg.core.domain.repository

import com.dimdimbjg.core.data.source.Resource
import com.dimdimbjg.core.domain.model.Detail
import com.dimdimbjg.core.domain.model.Games
import kotlinx.coroutines.flow.Flow

interface IGamesRepository {

    fun getAllGames(): Flow<Resource<List<Games>>>

    fun getAllFavoritesGames(): Flow<List<Games>>

    fun getGamesDetail(id: Int): Flow<Resource<Detail>>

    fun checkIsFavorite(id: Int): Flow<Boolean>

    suspend fun insertFavoriteGame(id: Int)

    suspend fun removeFavoriteGame(id: Int)

}