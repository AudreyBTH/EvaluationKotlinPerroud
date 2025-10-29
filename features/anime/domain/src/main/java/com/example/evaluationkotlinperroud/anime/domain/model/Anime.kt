package com.example.evaluationkotlinperroud.anime.domain.model

/**
 * Modèle de domaine représentant un anime.
 *
 * @property mal_id Identifiant unique de l’anime (MyAnimeList ID).
 * @property titles Liste des titres de l’anime.
 * @property imageUrl URL de l’image associée à l’anime.
 * @property season Saison de sortie de l’anime.
 * @property year Année de sortie de l’anime.
 * @property episodes Nombre d’épisodes.
 */
data class Anime (
    val mal_id: Int,
    val titles: List<Title>,
    val imageUrl: String?,
    val season: String?,
    val year: Int?,
    val episodes: Int?,

)

/**
 * Représente un titre d’anime.
 *
 * @property type Type du titre (ex : "Default", "Japanese").
 * @property title Chaîne du titre.
 */

data class Title(
    val type: String,
    val title: String
)