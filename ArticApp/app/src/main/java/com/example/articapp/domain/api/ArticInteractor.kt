package com.example.articapp.domain.api

import com.example.articapp.domain.models.ArtWorkEntity
import com.example.articapp.domain.models.SearchResults
import kotlinx.coroutines.flow.Flow

interface ArticInteractor {

    fun searchArtworks(query: String): Flow<SearchResults>

}