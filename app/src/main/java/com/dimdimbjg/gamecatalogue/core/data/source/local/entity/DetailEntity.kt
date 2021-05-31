package com.dimdimbjg.gamecatalogue.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "details")
data class DetailEntity(
    val rating: Double,
    @PrimaryKey
    @ColumnInfo(name = "games_id")
    val id: Int,
    val ratingsCount: Int,
    val released: String,
    val descriptionRaw: String,
    val backgroundImage: String,
    val name: String,
    val backgroundImageAdditional: String
)
