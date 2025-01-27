package com.example.articapp.domain.impl

import com.example.articapp.domain.api.ArticInteractor
import com.example.articapp.domain.api.ArticRepository
import com.example.articapp.domain.models.SearchResultsEntity
import com.example.articapp.data.models.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticInteractorImpl(private val repository: ArticRepository): ArticInteractor {

    override fun searchArtworks(query: String): Flow<SearchResultsEntity> {
        return repository.searchArtworks(query)
    }

}