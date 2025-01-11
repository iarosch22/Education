package com.example.articapp.domain.api

import com.example.articapp.domain.models.ArtWork
import com.example.articapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ArticRepository {

    fun searchArtworks(query: String): Flow<Resource<List<ArtWork>>>

}