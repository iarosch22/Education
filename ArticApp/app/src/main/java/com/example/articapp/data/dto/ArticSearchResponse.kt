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
    val id: Int,
    val thumbnail: Thumbnail,
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


