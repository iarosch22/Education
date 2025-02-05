package com.example.articapp.data.network

import com.example.articapp.data.dto.ArticSearchResponse
import com.example.articapp.data.dto.ArtworksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticApiService {

    @GET("artworks/search")
    suspend fun searchArtworks(@Query("q") query: String): ArticSearchResponse

    @GET("artists")
    suspend fun getArtworks(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): ArtworksResponse

}