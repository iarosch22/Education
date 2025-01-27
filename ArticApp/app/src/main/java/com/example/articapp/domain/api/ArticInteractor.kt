package com.example.articapp.domain.api

import com.example.articapp.data.models.Resource
import com.example.articapp.domain.models.ArtWorkEntity
import com.example.articapp.domain.models.SearchResultsEntity
import kotlinx.coroutines.flow.Flow

interface ArticInteractor {

    fun searchArtworks(query: String): Flow<SearchResultsEntity>

}