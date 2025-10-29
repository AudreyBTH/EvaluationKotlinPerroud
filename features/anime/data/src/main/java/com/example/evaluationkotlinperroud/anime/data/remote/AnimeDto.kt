package com.example.evaluationkotlinperroud.anime.data.remote

import kotlinx.serialization.Serializable

/**
 * Réponse de l’API contenant une liste d’animes.
 *
 * @property data Liste des objets [AnimeDto].
 */

@Serializable
data class AnimeResponse(
    val data: List<AnimeDto>
)

/**
 * Réponse de l’API contenant un seul anime.
 *
 * @property data Objet [AnimeDto] correspondant à un anime unique.
 */
@Serializable
data class SingleAnimeResponse(
    val data: AnimeDto
)

/**
 * Objet représentant un anime tel que renvoyé par l’API.
 *
 * @property mal_id Identifiant unique (MyAnimeList ID).
 * @property titles Liste des titres de l’anime.
 * @property images URLs des images associées à l’anime.
 * @property season Saison de sortie de l’anime.
 * @property year Année de sortie de l’anime.
 * @property episodes Nombre d’épisodes.
 */
@Serializable
data class AnimeDto(
    val mal_id: Int,
    val titles: List<TitleDto>,
    val images: ImagesDto? = null,
    val season: String? = null,
    val year: Int? = null,
    val episodes: Int? = null
)

/**
 * Représente un titre d’anime reçu de l’API.
 *
 * @property type Type de titre (ex : "Default", "Japanese").
 * @property title Chaîne du titre.
 */
@Serializable
data class TitleDto(
    val type: String,
    val title: String
)

/**
 * Contient les images de l’anime.
 *
 * @property jpg Objet [JpgDto] contenant l’URL de l’image JPEG.
 */
@Serializable
data class ImagesDto(
    val jpg: JpgDto
)

/**
 * Contient l’URL d’une image JPEG.
 *
 * @property image_url URL de l’image.
 */
@Serializable
data class JpgDto(
    val image_url: String
)
