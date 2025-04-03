package com.example.articapp.domain.models

import com.example.articapp.utils.MessageType

data class SearchResultsEntity(
    val artWorks: List<ArtWorkEntity>? = null,
    val messageType: MessageType? = null,
    val errorCode: String = "",
    val totalPages: Int? = null
)
