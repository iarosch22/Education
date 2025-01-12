package com.example.articapp.data.network

import com.example.articapp.data.dto.ArticSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticApiService {

    @GET("artworks/search")
    suspend fun searchArtworks(@Query("q") query: String): ArticSearchResponse

}