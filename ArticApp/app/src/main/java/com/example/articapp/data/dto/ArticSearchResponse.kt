package com.example.articapp.data.dto

import com.google.gson.annotations.SerializedName

data class ArticSearchResponse (
    val config: Config,
    val data: List<Data>,
): Response()

data class Config(
    @SerializedName("iiif_url")
    val imageUrl: String,
    @SerializedName("website_url")
    val websiteUrl: String
)

data class Data(
    @SerializedName("_score")
    val score: Double,
    @SerializedName("api_link")
    val apiLink: String,
    @SerializedName("api_model")
    val apiModel: String,
    val id: Int,
    @SerializedName("is_boosted")
    val isBoosted: Boolean,
    val thumbnail: Thumbnail,
    val timestamp: String,
    val title: String
)

data class Thumbnail(
    @SerializedName("alt_text")
    val altText: String,
    val height: Int,
    @SerializedName("lqip")
    val previewImage: String,
    val width: Int
)


