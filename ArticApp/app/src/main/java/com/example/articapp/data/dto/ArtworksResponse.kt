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
    @SerializedName("alt_titles")
    val altTitles: List<String>,
    @SerializedName("api_link")
    val apiLink: String,
    @SerializedName("api_model")
    val apiModel: String,
    @SerializedName("birth_date")
    val birthDate: Int,
    @SerializedName("death_date")
    val deathDate: Int,
    val description: Any,
    val id: Int,
    @SerializedName("is_artist")
    val isArtist: Boolean,
    @SerializedName("sort_title")
    val sortTitle: String,
    @SerializedName("source_updated_at")
    val sourceUpdatedAt: String,
    val timestamp: String,
    val title: String,
    @SerializedName("ulan_id")
    val ulanId: Any,
    @SerializedName("updated_at")
    val updatedAt: String
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
