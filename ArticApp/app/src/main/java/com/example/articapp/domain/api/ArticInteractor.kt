package com.example.articapp.domain.api

import com.example.articapp.domain.models.SearchResultsEntity
import kotlinx.coroutines.flow.Flow

interface ArticInteractor {

    fun searchArtworks(query: String): Flow<SearchResultsEntity>

    fun getArtworks(page: Int, limit: Int): Flow<SearchResultsEntity>
}