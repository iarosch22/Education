package com.example.articapp.data.network

import com.example.articapp.data.NetworkClient
import com.example.articapp.data.dto.ArticArtworkRequest
import com.example.articapp.data.dto.ArticSearchRequest
import com.example.articapp.data.dto.ArticSearchResponse
import com.example.articapp.data.dto.ArtworksResponse
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
                            imageUrl = "${imageUrl}/${it}/full/843,/0/default.jpg",
                            title = it.title,
                            previewImage = it.thumbnail.previewImage,
                        )
                    }
                    emit(SearchResultsEntity(data))
                }
            }
            400 -> {
                emit(SearchResultsEntity(errorType = ErrorType.UNKNOWN_ERROR))
            }
            500 -> {
                emit(SearchResultsEntity(errorType = ErrorType.DATABASE_ERROR))
            }
        }
    }

    override fun getArtworks(page: Int, limit: Int): Flow<SearchResultsEntity> = flow {
        val response = networkClient.doRequest(ArticArtworkRequest(page, limit))
        when(response.resultCode) {
            200 -> {
                with(response as ArtworksResponse) {
                    val imageUrl = response.config.imageUrl
                    val data = data.map {
                        ArtWorkEntity(
                            id = it.id,
                            imageUrl = "${imageUrl}/${it.imageId}/full/843,/0/default.jpg",
                            title = it.title,
                            pagination = pagination
                        )
                    }
                    emit(SearchResultsEntity(data))
                }
            }
            400 -> {
                emit(SearchResultsEntity(errorType = ErrorType.UNKNOWN_ERROR))
            }
            500 -> {
                emit(SearchResultsEntity(errorType = ErrorType.DATABASE_ERROR))
            }
        }
    }
}