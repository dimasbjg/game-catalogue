package com.dimdimbjg.core.data.source.remote

import com.dimdimbjg.core.data.source.remote.network.ApiResponse
import com.dimdimbjg.core.data.source.remote.network.ApiService
import com.dimdimbjg.core.data.source.remote.response.DetailGameResponse
import com.dimdimbjg.core.data.source.remote.response.ListGamesResponse
import com.dimdimbjg.core.utils.ErrorMessageHandler
import com.dimdimbjg.core.utils.IddlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource constructor(private val apiService: ApiService) {
    suspend fun getGamesList(): Flow<ApiResponse<ListGamesResponse>> {
        return flow {
            try {
                IddlingResource.increment()
                val result = apiService.getListGames()
                if (result.results.isEmpty()) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(result))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(ErrorMessageHandler.generateErrorMessage(e)))
            } finally {
                IddlingResource.decrement()
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGamesDetail(id: Int): Flow<ApiResponse<DetailGameResponse>> {
        return flow {
            try {
                IddlingResource.increment()
                val result = apiService.getDetailGames(id)
                emit(ApiResponse.Success(result))
            } catch (e: Exception) {
                emit(ApiResponse.Error(ErrorMessageHandler.generateErrorMessage(e)))
            } finally {
                IddlingResource.decrement()
            }
        }.flowOn(Dispatchers.IO)
    }
}