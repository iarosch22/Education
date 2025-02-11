package com.example.articapp.data.dto

import com.google.gson.annotations.SerializedName

data class ArticSearchResponse (
    val config: ConfigSearch,
    val data: List<DataSearch>,
): Response()

data class ConfigSearch(
    @SerializedName("iiif_url")
    val imageUrl: String,
    @SerializedName("website_url")
    val websiteUrl: String
)

data class DataSearch(
    @SerializedName("api_link")
    val apiLink: String,
    @SerializedName("api_model")
    val apiModel: String,
    val id: Int,
    @SerializedName("is_boosted")
    val isBoosted: Boolean,
    val thumbnail: Thumbnail,
    val timestamp: String,
    val title: String,
    @SerializedName("image_id")
    val imageId: String,
)

data class Thumbnail(
    @SerializedName("alt_text")
    val altText: String,
    val height: Int,
    @SerializedName("lqip")
    val previewImage: String,
    val width: Int
)


