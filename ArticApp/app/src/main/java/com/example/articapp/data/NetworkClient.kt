package com.example.articapp.data

import com.example.articapp.data.dto.ArticSearchResponse
import retrofit2.Response

interface NetworkClient<T> {

    suspend fun doRequest(dto: T): Response<ArticSearchResponse>

}