package com.example.evaluationkotlinperroud.injectiondependance

import org.koin.dsl.module
import io.ktor.client.engine.android.Android
import com.example.evaluationkotlinperroud.anime.data.remote.AnimeService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import androidx.room.Room
import com.example.evaluationkotlinperroud.anime.data.local.AnimeDatabase

/**
 * Module Koin pour l'injection de dépendances liées au réseau et à la base de données.
 */

val networkModule = module {


    /**
     * Fournit une instance de HttpClient configurée pour Android avec support JSON.
     */

    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }
        }
    }

    /**
     * Fournit une instance de AnimeService pour les appels réseau liés aux animes.
     */
    single { AnimeService(get()) }


    /**
     * Fournit une instance de AnimeDatabase pour accéder à la base de données locale.
     */
    single {
        Room.databaseBuilder(
            get(),
            AnimeDatabase::class.java,
            "anime-db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    /**
     * Fournit le DAO pour accéder aux données des animes dans la base de données.
     */

    single { get<AnimeDatabase>().animeDao() }
}
