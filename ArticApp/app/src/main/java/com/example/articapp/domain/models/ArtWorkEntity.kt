package com.example.articapp.domain.models

import com.example.articapp.data.dto.Pagination

data class ArtWorkEntity(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val previewImage: String? = null,
    val pagination: Pagination? = null
)
