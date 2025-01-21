package com.example.articapp.domain.models

import com.example.articapp.utils.ErrorType

data class SearchResultsEntity(
    val artWorks: List<ArtWorkEntity>?,
    val errorType: ErrorType?
)
