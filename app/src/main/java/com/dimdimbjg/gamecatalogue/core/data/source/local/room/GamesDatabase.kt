package com.dimdimbjg.gamecatalogue.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dimdimbjg.gamecatalogue.core.data.source.local.entity.DetailEntity
import com.dimdimbjg.gamecatalogue.core.data.source.local.entity.GamesEntity

@Database(
    entities = [GamesEntity::class, DetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GamesDatabase : RoomDatabase() {
    abstract fun gamesDao(): GamesDao
}