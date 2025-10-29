package com.example.evaluationkotlinperroud.anime.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entité représentant un anime dans la base de données locale.
 *
 * @property mal_id Identifiant unique de l’anime (MyAnimeList ID).
 * @property title Titre de l’anime.
 * @property imageUrl URL de l’image associée à l’anime.
 * @property season Saison de sortie de l’anime (ex : "Spring 2023").
 * @property episodes Nombre d’épisodes de l’anime.
 * @property rating Classification de l’anime (ex : "PG-13").
 * @property themes Thèmes principaux de l’anime.
 * @property liked Indique si l’anime est marqué comme "aimé" par l’utilisateur (par défaut false).
 */

@Entity(tableName = "animes")
data class AnimeEntity(
    @PrimaryKey val mal_id: Int,
    val title: String,
    val imageUrl: String?,
    val season: String?,
    val episodes: Int?,
    val rating: String?,
    val themes: String?,
    val liked: Boolean = false
)
