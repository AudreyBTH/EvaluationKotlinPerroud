package com.example.evaluationkotlinperroud.anime.domain.repository

import com.example.evaluationkotlinperroud.anime.domain.model.Anime

interface AnimeRepository {
    suspend fun getAnimes(): Result<List<Anime>>
    suspend fun getAnime(id : Int): Result<Anime>
}