package com.example.articapp.data.network

import com.example.articapp.data.NetworkClient
import com.example.articapp.data.dto.ArticSearchRequest
import com.example.articapp.data.dto.ArticSearchResponse
import com.example.articapp.domain.api.ArticRepository
import com.example.articapp.domain.models.ArtWorkEntity
import com.example.articapp.domain.models.SearchResultsEntity
import com.example.articapp.utils.ErrorType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient
): ArticRepository {

    override fun searchArtworks(query: String): Flow<SearchResultsEntity> = flow {
        val response = networkClient.doRequest(ArticSearchRequest(query))
        when(response.resultCode) {
            200 -> {
                with(response as ArticSearchResponse) {
                    val imageUrl = response.config.imageUrl
                    val data = data.map {
                        ArtWorkEntity(
                            id = it.id,
                            score = it.score,
                            apiLink = it.apiLink,
                            apiModel = it.apiModel,
                            imageUrl = "${imageUrl}/${it.id}/full/843,/0/default.jpg",
                            altText = it.thumbnail.altText,
                            previewImage = it.thumbnail.previewImage,
                        )
                    }
                    emit(SearchResultsEntity(data, null))
                }
            }
            400 -> {
                emit(SearchResultsEntity(null, ErrorType.UNKNOWN_ERROR))
            }
            500 -> {
                emit(SearchResultsEntity(null, ErrorType.DATABASE_ERROR))
            }
        }
    }
}