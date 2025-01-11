package com.example.articapp.data.network

data class ArtSearchResponse (
    val config: Config,
    val data: List<Data>,
)

data class Config(
    val iiif_url: String,
    val website_url: String
)

data class Data(
    val _score: Double,
    val api_link: String,
    val api_model: String,
    val id: Int,
    val is_boosted: Boolean,
    val thumbnail: Thumbnail,
    val timestamp: String,
    val title: String
)

data class Thumbnail(
    val alt_text: String,
    val height: Int,
    val lqip: String,
    val width: Int
)


