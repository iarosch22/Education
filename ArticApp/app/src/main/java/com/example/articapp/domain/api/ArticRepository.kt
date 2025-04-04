package com.example.articapp.domain.api

import com.example.articapp.domain.models.ArtWorkEntity
import com.example.articapp.domain.models.SearchResultsEntity
import kotlinx.coroutines.flow.Flow

interface ArticRepository {

    fun searchArtworks(query: String): Flow<SearchResultsEntity>

    fun getArtworksFromApi(page: Int = 1, limit: Int = 20): Flow<SearchResultsEntity>

}