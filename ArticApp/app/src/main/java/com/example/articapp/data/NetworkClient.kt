package com.example.articapp.data

import com.example.articapp.data.dto.Response

interface NetworkClient<T> {

    suspend fun doRequest(dto: T): Response

}