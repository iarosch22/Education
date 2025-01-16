package com.example.articapp.domain.impl

import com.example.articapp.domain.api.ArticInteractor
import com.example.articapp.domain.api.ArticRepository
import com.example.articapp.domain.models.ArtWorkEntity
import com.example.articapp.domain.models.SearchResults
import com.example.articapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticInteractorImpl(private val repository: ArticRepository): ArticInteractor {

    override fun searchArtworks(query: String): Flow<SearchResults> {
        return repository.searchArtworks(query).map { result ->
            when(result) {
                is Resource.Error -> SearchResults(null, result.errorType)
                is Resource.Success -> SearchResults(result.data, null)
            }
        }
    }

}