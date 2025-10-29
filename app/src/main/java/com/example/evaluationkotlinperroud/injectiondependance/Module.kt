package com.example.evaluationkotlinperroud.injectiondependance

import org.koin.dsl.module
import io.ktor.client.engine.android.Android
import com.example.evaluationkotlinperroud.anime.data.remote.AnimeService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


val networkModule = module {
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
    single { AnimeService(get()) }
}
