package com.example.evaluationkotlinperroud.anime.data.repository

import com.example.evaluationkotlinperroud.anime.data.local.AnimeDao
import com.example.evaluationkotlinperroud.anime.data.mapper.toDomain
import com.example.evaluationkotlinperroud.anime.data.mapper.toEntity
import com.example.evaluationkotlinperroud.anime.data.remote.AnimeService
import com.example.evaluationkotlinperroud.anime.domain.model.Anime
import com.example.evaluationkotlinperroud.anime.domain.repository.AnimeRepository

class AnimeRepositoryImpl(
    private val api: AnimeService,
    private val dao: AnimeDao
) {

    suspend fun getAnimes(forceRefresh: Boolean): Result<List<Anime>> = runCatching {
        // Récupération locale
        val local = dao.getAllAnimes().map { it.toDomain() }
        if (local.isNotEmpty() && !forceRefresh) return@runCatching local

        // Récupération distante
        val remote = api.animes()
        // Insertion en base avec insertAnimes
        dao.insertAnimes(remote.map { it.toEntity() })
        // Relecture pour retourner les données mises à jour
        dao.getAllAnimes().map { it.toDomain() }
    }

    suspend fun getAnime(id: Int): Anime = runCatching {
        dao.getAnimeById(id)?.toDomain() ?: api.anime(id.toString()).toDomain()
    }.getOrElse { throw it }
}
