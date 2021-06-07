package com.dimdimbjg.core.data.source.remote.network

import com.dimdimbjg.core.data.source.remote.response.DetailGameResponse
import com.dimdimbjg.core.data.source.remote.response.ListGamesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    companion object {
        private const val api_key = "6d9b0a98094f420bb20a15ecd77cd595"
    }

    @GET("games?key=$api_key&platform=4&exclude_additions=true&exclude_parents=true")
    suspend fun getListGames(): ListGamesResponse

    @GET("games/{id}?key=$api_key")
    suspend fun getDetailGames(@Path("id") id: Int): DetailGameResponse

}