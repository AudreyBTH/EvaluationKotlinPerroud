package com.example.evaluationkotlinperroud.anime.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class AnimeResponse(
    val data: List<AnimeDto>
)

@Serializable
data class SingleAnimeResponse(
    val data: AnimeDto
)

@Serializable
data class AnimeDto(
    val mal_id: Int,
    val titles: List<TitleDto>,
    val images: ImagesDto? = null,
    val season: String? = null,
    val year: Int? = null,
    val episodes: Int? = null
)

@Serializable
data class TitleDto(
    val type: String,
    val title: String
)

@Serializable
data class ImagesDto(
    val jpg: JpgDto
)

@Serializable
data class JpgDto(
    val image_url: String
)
