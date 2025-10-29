package com.example.evaluationkotlinperroud.anime.domain.model

data class Anime (
    val mal_id: Int,
    val titles: List<Title>,
    val imageUrl: String?,
    val season: String?,
    val year: Int?,
    val episodes: Int?,

)

data class Title(
    val type: String,
    val title: String
)