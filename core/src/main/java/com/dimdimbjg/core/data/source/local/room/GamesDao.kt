package com.dimdimbjg.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dimdimbjg.core.data.source.local.entity.DetailEntity
import com.dimdimbjg.core.data.source.local.entity.GamesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {

    @Query("SELECT * FROM games")
    fun getGames(): Flow<List<GamesEntity>>

    @Query("SELECT * FROM details WHERE games_id = :id")
    fun getGamesDetail(id: Int): Flow<DetailEntity>

    @Query("SELECT * FROM games WHERE isFavorites = 1")
    fun getFavoritesGames(): Flow<List<GamesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGames(games: List<GamesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetail(gamesDetail: DetailEntity)

    @Query("UPDATE games SET isFavorites = 1 WHERE id = :id")
    fun addFavorites(id: Int)

    @Query("UPDATE games SET isFavorites = 0 WHERE id = :id")
    fun removeFavorites(id: Int)

    @Query("SELECT isFavorites == 1 FROM games where id = :id")
    fun checkGameFavorite(id:Int): Flow<Boolean>

}