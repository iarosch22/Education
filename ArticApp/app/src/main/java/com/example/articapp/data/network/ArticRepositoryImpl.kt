package com.example.articapp.data.network

import com.example.articapp.data.NetworkClient
import com.example.articapp.data.dto.ArticSearchResponse
import com.example.articapp.data.dto.ArtworksResponse
import com.example.articapp.data.dto.BaseArticRequest
import com.example.articapp.domain.api.ArticRepository
import com.example.articapp.domain.models.ArtWorkEntity
import com.example.articapp.domain.models.SearchResultsEntity
import com.example.articapp.utils.MessageType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient
): ArticRepository {

    override fun searchArtworks(query: String): Flow<SearchResultsEntity> = flow {
        val response = networkClient.doRequest(BaseArticRequest.ArticSearchRequest(query))
        when(response.resultCode) {
            HttpURLConnection.HTTP_OK -> {
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
            HttpURLConnection.HTTP_BAD_REQUEST -> {
                emit(SearchResultsEntity(messageType = MessageType.UNKNOWN_ERROR))
            }
            HttpURLConnection.HTTP_INTERNAL_ERROR -> {
                emit(SearchResultsEntity(messageType = MessageType.DATABASE_ERROR))
            }
        }
    }

    override fun getArtworks(page: Int, limit: Int): Flow<SearchResultsEntity> = flow {
        try {
            val response = networkClient.doRequest(BaseArticRequest.ArticArtworkRequest(page, limit))
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
                emit(SearchResultsEntity(artWorks = data, totalPages = pagination.totalPages))
            }
        } catch (e: HttpException) {
            emit(SearchResultsEntity(
                messageType = MessageType.UNKNOWN_ERROR,
                errorCode = e.code().toString()
            ))
        }

    }
}