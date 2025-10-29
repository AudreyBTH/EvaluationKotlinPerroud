package com.example.evaluationkotlinperroud.anime.data.mapper

import com.example.evaluationkotlinperroud.anime.data.remote.AnimeDto
import com.example.evaluationkotlinperroud.anime.data.local.AnimeEntity
import com.example.evaluationkotlinperroud.anime.domain.model.Anime
import com.example.evaluationkotlinperroud.anime.domain.model.Title
import com.example.evaluationkotlinperroud.anime.data.remote.TitleDto
import com.example.evaluationkotlinperroud.anime.data.remote.ImagesDto
import com.example.evaluationkotlinperroud.anime.data.remote.JpgDto


fun AnimeDto.toDomain() = Anime(
    mal_id = mal_id,
    titles = titles.map { Title(it.type, it.title) },
    imageUrl = images?.jpg?.image_url,
    season = season,
    year = year,
    episodes = episodes
)

fun AnimeEntity.toDomain() = Anime(
    mal_id = mal_id,
    titles = listOf(Title("Default", title)),
    imageUrl = imageUrl,
    season = season,
    year = null,
    episodes = episodes
)


fun Anime.toDto() = AnimeDto(
    mal_id = mal_id,
    titles = titles.map { TitleDto(it.type, it.title) },
    images = imageUrl?.let { ImagesDto(JpgDto(it)) },
    season = season,
    year = year,
    episodes = episodes
)

fun AnimeDto.toEntity() = AnimeEntity(
    mal_id = mal_id,
    title = titles.firstOrNull()?.title ?: "Unknown",
    imageUrl = images?.jpg?.image_url,
    season = season,
    episodes = episodes,
    rating = null,  // si tu n’as pas encore de donnée
    themes = null   // idem
)
