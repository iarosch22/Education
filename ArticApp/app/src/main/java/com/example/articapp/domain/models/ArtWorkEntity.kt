package com.example.articapp.domain.models

import android.icu.text.CaseMap.Title
import com.example.articapp.data.dto.Pagination

data class ArtWorkEntity(
    val id: Int,
    val apiLink: String,
    val apiModel: String,
    val imageUrl: String,
    val title: String,
    val previewImage: String? = null,
    val pagination: Pagination? = null
)
