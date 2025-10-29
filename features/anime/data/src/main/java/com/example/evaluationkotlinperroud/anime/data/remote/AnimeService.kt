package com.example.evaluationkotlinperroud.anime.data.remote

import com.example.evaluationkotlinperroud.anime.domain.model.Anime
import io.ktor.client.* 
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.ContentType


    class AnimeService(private val client: HttpClient) {
        val url = "https://api.jikan.moe/v4"
        suspend fun animes(): List<AnimeDto> {
            val response = client.get("$url/seasons/now") {
                accept(ContentType.Application.Json)
            }.body<AnimeResponse>()
            return response.data
        }

        suspend fun anime(id: String): AnimeDto {
            val response = client.get("$url/anime/$id"){
                accept(ContentType.Application.Json)
            }.body<SingleAnimeResponse>()
            return response.data
        }
    }



