package com.example.articapp.domain.impl

import com.example.articapp.domain.api.ArticInteractor
import com.example.articapp.domain.api.ArticRepository
import com.example.articapp.domain.models.SearchResultsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticInteractorImpl @Inject constructor(
    private val repository: ArticRepository
): ArticInteractor {

    override fun searchArtworks(query: String): Flow<SearchResultsEntity> {
        return repository.searchArtworks(query)
    }

    override fun getArtworks(page: Int, limit: Int): Flow<SearchResultsEntity> {
        return repository.getArtworksFromApi(page = page, limit = limit)
    }

}