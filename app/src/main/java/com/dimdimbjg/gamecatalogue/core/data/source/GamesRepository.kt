package com.dimdimbjg.gamecatalogue.core.data.source

import com.dimdimbjg.gamecatalogue.core.data.source.local.LocalDataSource
import com.dimdimbjg.gamecatalogue.core.data.source.remote.RemoteDataSource
import com.dimdimbjg.gamecatalogue.core.data.source.remote.network.ApiResponse
import com.dimdimbjg.gamecatalogue.core.data.source.remote.response.DetailGameResponse
import com.dimdimbjg.gamecatalogue.core.data.source.remote.response.ListGamesResponse
import com.dimdimbjg.gamecatalogue.core.domain.model.Detail
import com.dimdimbjg.gamecatalogue.core.domain.model.Games
import com.dimdimbjg.gamecatalogue.core.domain.repository.IGamesRepository
import com.dimdimbjg.gamecatalogue.core.utils.AppExecutors
import com.dimdimbjg.gamecatalogue.core.utils.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GamesRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGamesRepository {
    override fun getAllGames(): Flow<Resource<List<Games>>> =
        object : NetworkBoundResource<List<Games>, ListGamesResponse>(appExecutors) {
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
        object : NetworkBoundResource<Detail, DetailGameResponse>(appExecutors) {
            override fun loadFromDB(): Flow<Detail> {
                return localDataSource.getDetail().map {
                    if (it == null) {
                        Detail.createEmptyObject()
                    } else {
                        Mapper.mapDetailEntitiesToDomain(it)
                    }
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

    override suspend fun insertFavoriteGame(id: Int) {
        localDataSource.addFavorites(id)
    }

    override suspend fun removeFavoriteGame(id: Int) {
        localDataSource.removeFavorites(id)
    }


}