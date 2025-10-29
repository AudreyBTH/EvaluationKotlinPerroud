package com.example.evaluationkotlinperroud.anime.data.remote

import io.ktor.client.* 
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.ContentType

/**
 * Service pour interagir avec l'API Jikan afin de récupérer des informations sur les animes.
 *
 * @property client Client HTTP utilisé pour effectuer les requêtes réseau.
 */
    class AnimeService(private val client: HttpClient) {

    /** URL de base de l'API Jikan */
        val url = "https://api.jikan.moe/v4"

    /**
     * Récupère la liste des animes actuellement en diffusion.
     *
     * @return Liste d’[AnimeDto] correspondant aux animes de la saison en cours.
     */
        suspend fun animes(): List<AnimeDto> {
            val response = client.get("$url/seasons/now") {
                accept(ContentType.Application.Json)
            }.body<AnimeResponse>()
            return response.data
        }

    /**
     * Récupère les détails d’un anime spécifique à partir de son identifiant.
     *
     * @param id Identifiant de l’anime (MyAnimeList ID).
     * @return Un objet [AnimeDto] contenant les informations de l’anime.
     */

        suspend fun anime(id: String): AnimeDto {
            val response = client.get("$url/anime/$id"){
                accept(ContentType.Application.Json)
            }.body<SingleAnimeResponse>()
            return response.data
        }
    }



