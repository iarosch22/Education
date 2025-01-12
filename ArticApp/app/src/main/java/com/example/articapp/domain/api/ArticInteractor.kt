package com.example.articapp.domain.api

import com.example.articapp.domain.models.ArtWork
import kotlinx.coroutines.flow.Flow

interface ArticInteractor {

    fun searchArtworks(query: String): Flow<Pair<List<ArtWork>?, String?>>

}