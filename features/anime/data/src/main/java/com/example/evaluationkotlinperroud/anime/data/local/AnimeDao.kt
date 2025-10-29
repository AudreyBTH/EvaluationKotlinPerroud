package com.example.evaluationkotlinperroud.anime.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: AnimeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimes(animes: List<AnimeEntity>)

    @Query("SELECT * FROM animes")
    suspend fun getAllAnimes(): List<AnimeEntity>

    @Query("SELECT * FROM animes WHERE mal_id = :id")
    suspend fun getAnimeById(id: Int): AnimeEntity?
}
