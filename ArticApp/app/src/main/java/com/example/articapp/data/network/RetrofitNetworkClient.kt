package com.example.articapp.data.network

import android.content.Context
import com.example.articapp.data.NetworkClient
import com.example.articapp.data.dto.Response

class RetrofitNetworkClient(private val context: Context): NetworkClient {

    private val ArticBaseUrl = "https://api.artic.edu/api/v1/"

    override suspend fun doRequest(dto: Any): Response {
        TODO("Not yet implemented")
    }

}