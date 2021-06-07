package com.dimdimbjg.core.data.source

import com.dimdimbjg.core.data.source.local.LocalDataSource
import com.dimdimbjg.core.data.source.remote.RemoteDataSource
import com.dimdimbjg.core.data.source.remote.network.ApiResponse
import com.dimdimbjg.core.data.source.remote.response.DetailGameResponse
import com.dimdimbjg.core.data.source.remote.response.ListGamesResponse
import com.dimdimbjg.core.domain.model.Detail
import com.dimdimbjg.core.domain.model.Games
import com.dimdimbjg.core.domain.repository.IGamesRepository
import com.dimdimbjg.core.utils.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

class GamesRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IGamesRepository {
    override fun getAllGames(): Flow<Resource<List<Games>>> =
        object :
            NetworkBoundResource<List<Games>, ListGamesResponse>() {
            override fun loadFromDB(): Flow<List<Games>> {
                return localDataSource.getGames().map {
                    Mapper.mapGamesEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Games>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<ListGamesResponse>> {
                return remoteDataSource.getGamesList()
            }

            override suspend fun saveCallResult(data: ListGamesResponse) {
                val gamesList = Mapper.mapGamesResponseToEntities(data)
                localDataSource.insertGames(gamesList)
            }
        }.asFlow()

    override fun getAllFavoritesGames(): Flow<List<Games>> =
        localDataSource.getFavorites().map {
            Mapper.mapGamesEntitiesToDomain(it)
        }


    override fun getGamesDetail(id: Int): Flow<Resource<Detail>> =
        object : NetworkBoundResource<Detail, DetailGameResponse>() {
            override fun loadFromDB(): Flow<Detail> =
                localDataSource.getDetail(id).mapNotNull {
                    if (it == null) {
                        Detail.createEmptyObject()
                    } else {
                        Mapper.mapDetailEntitiesToDomain(it)
                    }
                }


            override fun shouldFetch(data: Detail?): Boolean = data?.id == -1

            override suspend fun createCall(): Flow<ApiResponse<DetailGameResponse>> {
                return remoteDataSource.getGamesDetail(id)
            }

            override suspend fun saveCallResult(data: DetailGameResponse) {
                val gameDetail = Mapper.mapDetailResponseToEntities(data)
                localDataSource.insertDetail(gameDetail)
            }

        }.asFlow()

    override fun checkIsFavorite(id: Int): Flow<Boolean> {
        return localDataSource.checkFavorites(id)
    }

    override suspend fun insertFavoriteGame(id: Int) {
        localDataSource.addFavorites(id)
    }

    override suspend fun removeFavoriteGame(id: Int) {
        localDataSource.removeFavorites(id)
    }


}