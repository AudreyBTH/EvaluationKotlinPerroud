package com.example.evaluationkotlinperroud.anime.data.repository

import com.example.evaluationkotlinperroud.anime.data.local.AnimeDao
import com.example.evaluationkotlinperroud.anime.data.mapper.toDomain
import com.example.evaluationkotlinperroud.anime.data.mapper.toEntity
import com.example.evaluationkotlinperroud.anime.data.remote.AnimeService
import com.example.evaluationkotlinperroud.anime.domain.model.Anime
/**
 * Implémentation du [com.example.evaluationkotlinperroud.anime.domain.repository.AnimeRepository] pour gérer la récupération des animes
 * depuis l'API distante et la base de données locale.
 *
 * @property api Service pour interagir avec l'API Jikan.
 * @property dao DAO pour accéder aux données locales des animes.
 */

class AnimeRepositoryImpl(
    private val api: AnimeService,
    private val dao: AnimeDao
) {
    /**
     * Récupère la liste des animes.
     *
     * Si [forceRefresh] est vrai ou si la base locale est vide, récupère depuis l'API et met à jour la base locale.
     * Sinon, retourne les données locales.
     *
     * @param forceRefresh Indique s'il faut forcer le rafraîchissement depuis l'API.
     * @return [Result] contenant la liste des [Anime] ou l'erreur si la récupération échoue.
     */
    suspend fun getAnimes(forceRefresh: Boolean): Result<List<Anime>> = runCatching {
        // Récupération locale
        val local = dao.getAllAnimes().map { it.toDomain() }
        if (local.isNotEmpty() && !forceRefresh) return@runCatching local


        val remote = api.animes()

        dao.insertAnimes(remote.map { it.toEntity() })

        dao.getAllAnimes().map { it.toDomain() }
    }

    /**
     * Récupère un anime spécifique par son identifiant.
     *
     * Cherche d'abord dans la base locale, sinon récupère depuis l'API.
     *
     * @param id Identifiant de l'anime (MyAnimeList ID).
     * @return L’objet [Anime] correspondant.
     * @throws Exception Si la récupération échoue.
     */
    suspend fun getAnime(id: Int): Anime = runCatching {
        dao.getAnimeById(id)?.toDomain() ?: api.anime(id.toString()).toDomain()
    }.getOrElse { throw it }
}
