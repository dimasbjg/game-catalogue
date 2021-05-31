package com.dimdimbjg.gamecatalogue.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class GamesEntity(
    @PrimaryKey
    val id: Int,
    val backgroundImage: String,
    val name: String,
    val isFavorites: Boolean = false
)
