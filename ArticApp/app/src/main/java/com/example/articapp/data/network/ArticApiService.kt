package com.example.articapp.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticApiService {

    @GET("/artworks/search")
    suspend fun searchArtworks(@Query("q") query: String): ArtsSearchResponse

}