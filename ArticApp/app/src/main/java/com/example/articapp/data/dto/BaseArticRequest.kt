package com.example.articapp.data.dto

sealed class BaseArticRequest {

    data class ArticSearchRequest(val query: String): BaseArticRequest()
    data class ArticArtworkRequest(val page: Int, val limit: Int): BaseArticRequest()

}