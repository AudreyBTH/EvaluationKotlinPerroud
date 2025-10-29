package com.example.evaluationkotlinperroud.anime.domain.repository

import com.example.evaluationkotlinperroud.anime.domain.model.Anime
/**
 * Interface définissant le contrat pour la récupération des animes.
 */
interface AnimeRepository {

    /**
     * Récupère la liste de tous les animes.
     *
     * @return [Result] contenant la liste des [Anime] ou l’erreur si la récupération échoue.
     */
    suspend fun getAnimes(): Result<List<Anime>>

    /**
     * Récupère un anime spécifique par son identifiant.
     *
     * @param id Identifiant de l’anime (MyAnimeList ID).
     * @return [Result] contenant l’objet [Anime] ou l’erreur si la récupération échoue.
     */
    suspend fun getAnime(id : Int): Result<Anime>
}