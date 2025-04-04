package com.example.articapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.articapp.data.NetworkClient
import com.example.articapp.data.dto.BaseArticRequest
import com.example.articapp.data.dto.Response
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticRetrofitNetworkClient @Inject constructor(
    @ApplicationContext private val context: Context
): NetworkClient {

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

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }

        }

        return false
    }
}