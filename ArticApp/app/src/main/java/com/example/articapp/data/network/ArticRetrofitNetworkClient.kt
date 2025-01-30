package com.example.articapp.data.network

import com.example.articapp.data.NetworkClient
import com.example.articapp.data.dto.ArticSearchRequest
import com.example.articapp.data.dto.ArticSearchResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticRetrofitNetworkClient @Inject constructor(): NetworkClient<ArticSearchRequest> {

    private val articBaseUrl = "https://api.artic.edu/api/v1/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(articBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val arcticApiService = retrofit.create(ArticApiService::class.java)

    override suspend fun doRequest(dto: ArticSearchRequest): Response<ArticSearchResponse> {

        return try {
                arcticApiService.searchArtworks(dto.query)
            } catch (e: Throwable) {
                Response.error(500, ResponseBody.create(null, "Internal Error"))
            }
        }
}