package com.example.evaluationkotlinperroud

import android.app.Application
import com.example.evaluationkotlinperroud.injectiondependance.networkModule
import com.example.evaluationkotlinperroud.anime.api.AnimeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Classe Application de l'application EvaluationKotlinPerroud.
 *
 * Initialise Koin pour l'injection de dépendances au démarrage de l'application.
 */

class EvaluationKotlinPerroudApplication : Application() {

    /**
     * Appelée lors de la création de l'application.
     * Initialise le contexte Koin et les modules nécessaires.
     */

    override fun onCreate ()  {
        super.onCreate()
        startKoin {
            androidContext(this@EvaluationKotlinPerroudApplication)
            modules(AnimeModule,networkModule)
        }
    }
}