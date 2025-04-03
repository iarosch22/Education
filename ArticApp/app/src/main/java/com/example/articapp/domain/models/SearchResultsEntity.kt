package com.example.articapp.domain.models

import com.example.articapp.presentation.MessageType

data class SearchResultsEntity(
    val artWorks: List<ArtWorkEntity>? = null,
    val messageType: MessageType? = null,
    val errorCode: String = "",
    val totalPages: Int? = null
)
