package com.example.articapp.data.network

import android.util.Log
import com.example.articapp.data.NetworkClient
import com.example.articapp.data.db.AppDatabase
import com.example.articapp.data.db.ArtWorkDbEntity
import com.example.articapp.data.dto.ArticSearchResponse
import com.example.articapp.data.dto.ArtworksResponse
import com.example.articapp.data.dto.BaseArticRequest
import com.example.articapp.domain.api.ArticRepository
import com.example.articapp.domain.models.ArtWorkEntity
import com.example.articapp.domain.models.SearchResultsEntity
import com.example.articapp.presentation.MessageType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient,
    private val appDatabase: AppDatabase
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
                emit(SearchResultsEntity(
                    messageType = MessageType.UNKNOWN_ERROR,
                    errorCode = HttpURLConnection.HTTP_BAD_REQUEST.toString()
                ))
            }
            HttpURLConnection.HTTP_INTERNAL_ERROR -> {
                emit(SearchResultsEntity(messageType = MessageType.DATABASE_ERROR))
            }
        }
    }

    override fun getArtworksFromApi(page: Int, limit: Int): Flow<SearchResultsEntity> = flow {
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
                saveArtworksInDb(data)
                emit(SearchResultsEntity(artWorks = data, totalPages = pagination.totalPages))
            }
        } catch (e: HttpException) {
            emit(SearchResultsEntity(
                messageType = MessageType.UNKNOWN_ERROR,
                errorCode = e.code().toString()
            ))
        }

    }

    override suspend fun saveArtworksInDb(artworks: List<ArtWorkEntity>) {
        val artworksForDb = artworks.map { artwork ->
            ArtWorkDbEntity(
                id = artwork.id,
                imageUrl = artwork.imageUrl,
                title = artwork.title
            )
        }

        appDatabase.artworksDao().insertArtworks(artworksForDb)
    }

    override suspend fun getArtworksFromDb(): List<ArtWorkEntity> {
        return appDatabase.artworksDao().getArtworks().map {
            ArtWorkEntity(
                id = it.id,
                imageUrl = it.imageUrl,
                title = it.title,
            )
        }
    }

}