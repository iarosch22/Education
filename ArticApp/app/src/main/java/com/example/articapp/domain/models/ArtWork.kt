package com.example.articapp.domain.models

data class ArtWork(
    val id: Int,
    val score: Double,
    val api_link: String,
    val api_model: String,
    val imageUrl: String,
    val alt_text: String,
    val previewImage: String,
)
