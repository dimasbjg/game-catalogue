package com.dimdimbjg.core.utils

import com.dimdimbjg.core.data.source.local.entity.DetailEntity
import com.dimdimbjg.core.data.source.local.entity.GamesEntity
import com.dimdimbjg.core.data.source.remote.response.DetailGameResponse
import com.dimdimbjg.core.data.source.remote.response.ListGamesResponse
import com.dimdimbjg.core.domain.model.Detail
import com.dimdimbjg.core.domain.model.Games

object Mapper {
    fun mapGamesResponseToEntities(data: ListGamesResponse): List<GamesEntity> {
        val list = ArrayList<GamesEntity>()
        data.results.map {
            val games = GamesEntity(
                id = it.id,
                name = it.name,
                backgroundImage = it.backgroundImage,
                isFavorites = false
            )
            list.add(games)
        }
        return list
    }

    fun mapGamesEntitiesToDomain(data: List<GamesEntity>): List<Games> =
        data.map {
            Games(
                id = it.id,
                name = it.name,
                backgroundImage = it.backgroundImage,
                isFavorites = it.isFavorites
            )
        }

    fun mapGamesDomainToEntity(data: Games) = GamesEntity(
        id = data.id,
        name = data.name,
        backgroundImage = data.backgroundImage,
        isFavorites = data.isFavorites
    )

    fun mapDetailResponseToEntities(data: DetailGameResponse): DetailEntity =
        DetailEntity(
            name = data.name,
            backgroundImage = data.backgroundImage,
            id = data.id,
            descriptionRaw = data.descriptionRaw,
            backgroundImageAdditional = data.backgroundImageAdditional,
            rating = data.rating,
            ratingsCount = data.ratingsCount,
            released = data.released
        )

    fun mapDetailEntitiesToDomain(data: DetailEntity): Detail =
        Detail(
            data.rating,
            data.id,
            data.ratingsCount,
            data.released,
            data.descriptionRaw,
            data.backgroundImage,
            data.name,
            data.backgroundImageAdditional
        )

    fun mapDetailDomainToEntity(data: Detail): DetailEntity =
        DetailEntity(
            data.rating,
            data.id,
            data.ratingsCount,
            data.released,
            data.descriptionRaw,
            data.backgroundImage,
            data.name,
            data.backgroundImageAdditional
        )

}