package com.example.articapp.ui.models

import com.example.articapp.domain.models.ArtWorkEntity

sealed interface ArticState {

    data class Error(val errorMessage: String): ArticState

    data class Content(val artworks: List<ArtWorkEntity>): ArticState

    data class Empty(val message: String): ArticState

}