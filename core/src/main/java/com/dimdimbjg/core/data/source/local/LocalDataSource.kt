package com.dimdimbjg.core.data.source.local

import com.dimdimbjg.core.data.source.local.entity.DetailEntity
import com.dimdimbjg.core.data.source.local.entity.GamesEntity
import com.dimdimbjg.core.data.source.local.room.GamesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalDataSource constructor(private val gamesDao: GamesDao) {
    suspend fun insertGames(games: List<GamesEntity>) {
        withContext(Dispatchers.IO) { gamesDao.insertGames(games) }
    }

    suspend fun insertDetail(detail: DetailEntity) {
        withContext(Dispatchers.IO) { gamesDao.insertDetail(detail) }
    }

    fun getGames(): Flow<List<GamesEntity>> {
        return gamesDao.getGames()
    }

    fun getDetail(id: Int): Flow<DetailEntity> {
        return gamesDao.getGamesDetail(id)
    }

    fun getFavorites(): Flow<List<GamesEntity>> {
        return gamesDao.getFavoritesGames()
    }

    fun checkFavorites(id: Int): Flow<Boolean> {
        return gamesDao.checkGameFavorite(id)
    }

    suspend fun addFavorites(id: Int) {
        withContext(Dispatchers.IO) { gamesDao.addFavorites(id) }
    }

    suspend fun removeFavorites(id: Int) {
        withContext(Dispatchers.IO) { gamesDao.removeFavorites(id) }
    }

}