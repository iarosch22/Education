package com.example.articapp.data.network

import com.example.articapp.data.dto.Response

class ArtsSearchResponse(val data: List<Data>): Response()

data class Data(
    val _score: Double,
    val api_link: String,
    val api_model: String,
    val id: Int,
    val is_boosted: Boolean,
    val thumbnail: Thumbnail,
    val timestamp: String,
    val title: String
)

data class Thumbnail(
    val alt_text: String,
    val height: Int,
    val lqip: String,
    val width: Int
)
