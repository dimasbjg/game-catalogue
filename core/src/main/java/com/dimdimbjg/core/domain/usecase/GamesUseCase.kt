package com.dimdimbjg.core.domain.usecase

import androidx.lifecycle.LiveData
import com.dimdimbjg.core.data.source.Resource
import com.dimdimbjg.core.domain.model.Detail
import com.dimdimbjg.core.domain.model.Games
import kotlinx.coroutines.flow.Flow

interface GamesUseCase {
    fun getAllGames(): Flow<Resource<List<Games>>>

    fun getAllFavoritesGames(): Flow<List<Games>>

    fun getGamesDetail(id: Int): Flow<Resource<Detail>>

    fun checkIsFavorites(id: Int): LiveData<Boolean>

    suspend fun addGameFavorites(id: Int)
    suspend fun removeGameFavorites(id: Int)


}