package com.example.evaluationkotlinperroud

import android.app.Application
import com.example.evaluationkotlinperroud.injectiondependance.networkModule
import com.example.evaluationkotlinperroud.anime.api.AnimeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class EvaluationKotlinPerroudApplication : Application() {
    override fun onCreate ()  {
        super.onCreate()
        startKoin {
            androidContext(this@EvaluationKotlinPerroudApplication)
            modules(AnimeModule,networkModule)
        }
    }
}