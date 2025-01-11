package com.example.articapp.data

import com.example.articapp.data.dto.Response

interface NetworkClient {

    suspend fun doRequest(dto: Any): Response

}