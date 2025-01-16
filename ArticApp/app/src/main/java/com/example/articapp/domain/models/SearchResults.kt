package com.example.articapp.domain.models

import com.example.articapp.utils.ErrorType

data class SearchResults(
    val artWorks: List<ArtWorkEntity>?,
    val errorType: ErrorType?
)
