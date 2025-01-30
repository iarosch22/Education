package com.example.articapp.domain.api

import com.example.articapp.domain.models.SearchResultsEntity
import kotlinx.coroutines.flow.Flow

interface ArticRepository {

    fun searchArtworks(query: String): Flow<SearchResultsEntity>

}