package com.example.articapp.data.network

import android.content.Context
import android.util.Log
import com.example.articapp.data.NetworkClient
import com.example.articapp.data.dto.ArticSearchRequest
import com.example.articapp.data.dto.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitNetworkClient: NetworkClient<ArticSearchRequest> {

    private val articBaseUrl = "https://api.artic.edu/api/v1/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(articBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val arcticApiService = retrofit.create(ArticApiService::class.java)

    override suspend fun doRequest(dto: ArticSearchRequest): Response {

        return try {
                arcticApiService.searchArtworks(dto.query).apply {  resultCode = 200 }
            } catch (e: Throwable) {
                Response().apply { resultCode = 500 }
            }
        }
}