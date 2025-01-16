package com.example.articapp.data.network

import com.example.articapp.data.NetworkClient
import com.example.articapp.data.dto.ArticSearchRequest
import com.example.articapp.data.dto.ArticSearchResponse
import com.example.articapp.domain.api.ArticRepository
import com.example.articapp.domain.models.ArtWorkEntity
import com.example.articapp.utils.ErrorType
import com.example.articapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class ArticRepositoryImpl(private val networkClient: NetworkClient<ArticSearchRequest>): ArticRepository {

    override fun searchArtworks(query: String): Flow<Resource<List<ArtWorkEntity>>> = flow {
        val response = networkClient.doRequest(ArticSearchRequest(query))
        when(response.resultCode) {
            -1 -> emit(Resource.Error(errorType = ErrorType.UNKNOWN_ERROR))
            200 -> {
                with(response as ArticSearchResponse) {
                    val imageUrl = config.imageUrl
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
                    emit(Resource.Success(data))
                }
            }
            else -> emit(Resource.Error(errorType = ErrorType.NETWORK_ERROR))
        }
    }
}