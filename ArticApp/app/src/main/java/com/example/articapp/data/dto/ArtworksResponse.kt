package com.example.articapp.data.dto

import com.google.gson.annotations.SerializedName

data class ArtworksResponse(
    val config: ConfigArtworks,
    val data: List<DataArtworks>,
    val pagination: Pagination
): Response()

data class ConfigArtworks(
    @SerializedName("iiif_url")
    val imageUrl: String,
    @SerializedName("website_url")
    val websiteUrl: String
)

data class DataArtworks(
    val description: Any,
    val id: Int,
    val title: String,
    @SerializedName("image_id")
    val imageId: String,
)

data class Pagination(
    @SerializedName("current_page")
    val currentPage: Int,
    val limit: Int,
    @SerializedName("next_url")
    val nextUrl: String,
    val offset: Int,
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)
