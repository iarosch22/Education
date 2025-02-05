package com.example.articapp.data.network

import com.example.articapp.data.NetworkClient
import com.example.articapp.data.dto.ArticArtworkRequest
import com.example.articapp.data.dto.ArticSearchRequest
import com.example.articapp.data.dto.BaseArticRequest
import com.example.articapp.data.dto.Response
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticRetrofitNetworkClient @Inject constructor(): NetworkClient {

    private val articBaseUrl = "https://api.artic.edu/api/v1/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(articBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val arcticApiService = retrofit.create(ArticApiService::class.java)

    override suspend fun doRequest(dto: BaseArticRequest): Response {
        return try {
                val response = when(dto) {
                    is ArticSearchRequest -> arcticApiService.searchArtworks(dto.query)
                    is ArticArtworkRequest -> arcticApiService.getArtworks(dto.page, dto.limit)
                    else -> {
                        return Response().apply { resultCode = 400 }
                    }
                }
                response.apply { resultCode = 200 }
            } catch (e: Throwable) {
                Response().apply { resultCode = 500 }
            }
        }
}