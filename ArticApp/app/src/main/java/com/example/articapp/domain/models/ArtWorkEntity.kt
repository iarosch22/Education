package com.example.articapp.domain.models

data class ArtWorkEntity(
    val id: Int,
    val score: Double,
    val apiLink: String,
    val apiModel: String,
    val imageUrl: String,
    val altText: String,
    val previewImage: String,
)
