package com.example.articapp.ui.models

import com.example.articapp.domain.models.ArtWork

sealed interface ArticState {

    data class Error(val errorMessage: String): ArticState

    data class Content(val artworks: List<ArtWork>): ArticState

    data class Empty(val message: String): ArticState

}