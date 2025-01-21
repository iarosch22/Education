package com.example.articapp.data.network

import com.example.articapp.data.NetworkClient
import com.example.articapp.data.dto.ArticSearchRequest
import com.example.articapp.data.dto.ArticSearchResponse
import com.example.articapp.domain.api.ArticRepository
import com.example.articapp.domain.models.ArtWorkEntity
import com.example.articapp.utils.ErrorType
import com.example.articapp.data.models.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArticRepositoryImpl(private val networkClient: NetworkClient<ArticSearchRequest>): ArticRepository {

    override fun searchArtworks(query: String): Flow<Resource<List<ArtWorkEntity>>> = flow {
        val response = networkClient.doRequest(ArticSearchRequest(query))
        if (response.isSuccessful) {
            val body = response.body()

            if (body != null) {
                val imageUrl = body.config.imageUrl
                val data = body.data.map {
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
                emit(Resource.Success(data))
            } else {
                emit(Resource.Error(errorType = ErrorType.DATABASE_ERROR))
            }
        } else {
            emit(Resource.Error(errorType = ErrorType.NETWORK_ERROR))
        }
    }
}