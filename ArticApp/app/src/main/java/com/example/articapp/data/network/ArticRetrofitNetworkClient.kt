package com.example.articapp.data.network

import com.example.articapp.data.NetworkClient
import com.example.articapp.data.dto.BaseArticRequest
import com.example.articapp.data.dto.Response
import retrofit2.HttpException
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
        return when(dto) {
            is BaseArticRequest.ArticSearchRequest -> arcticApiService.searchArtworks(dto.query)
            is BaseArticRequest.ArticArtworkRequest -> arcticApiService.getArtworks(dto.page, dto.limit)
        }
    }
}