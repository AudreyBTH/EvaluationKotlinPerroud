package com.example.evaluationkotlinperroud.anime.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animes")
data class AnimeEntity(
    @PrimaryKey val mal_id: Int,
    val title: String,
    val imageUrl: String?,
    val season: String?,
    val episodes: Int?,
    val rating: String?,
    val themes: String?
)
