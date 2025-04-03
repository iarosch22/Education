package com.example.articapp.presentation.ui.models

import com.example.articapp.domain.models.ArtWorkEntity
import com.example.articapp.presentation.MessageType

sealed interface ArticState {

    data object Loading: ArticState

    data class Error(val messageType: MessageType, val errorCode: String = ""): ArticState

    data class Content(val artworks: List<ArtWorkEntity>): ArticState

}