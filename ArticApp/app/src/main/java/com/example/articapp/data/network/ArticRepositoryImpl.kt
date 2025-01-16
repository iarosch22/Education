package com.example.articapp.data.network

import com.example.articapp.data.NetworkClient
import com.example.articapp.data.dto.ArticSearchRequest
import com.example.articapp.data.dto.ArticSearchResponse
import com.example.articapp.domain.api.ArticRepository
import com.example.articapp.domain.models.ArtWork
import com.example.articapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class ArticRepositoryImpl(private val networkClient: NetworkClient<ArticSearchRequest>): ArticRepository {

    override fun searchArtworks(query: String): Flow<Resource<List<ArtWork>>> = flow {
        val response = networkClient.doRequest(ArticSearchRequest(query))
        withContext(Dispatchers.IO) {
            when(response.resultCode) {
                -1 -> emit(Resource.Error("Проверьте запрос"))
                200 -> {
                    with(response as ArticSearchResponse) {
                        val imageUrl = config.imageUrl
                        val data = data.map {
                            ArtWork(
                                id = it.id,
                                score = it.score,
                                api_link = it.apiLink,
                                api_model = it.apiModel,
                                imageUrl = "${imageUrl}/${it.id}/full/843,/0/default.jpg",
                                alt_text = it.thumbnail.altText,
                                previewImage = it.thumbnail.previewImage,
                            )
                        }
                        emit(Resource.Success(data))
                    }
                }
                else -> emit(Resource.Error("Ошибка сервера"))
            }
        }
    }
}