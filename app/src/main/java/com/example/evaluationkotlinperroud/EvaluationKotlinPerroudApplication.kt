package com.example.evaluationkotlinperroud

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EvaluationDiiageKotlinApplication : Application() {
    override fun onCreate ()  {
        super.onCreate()
        startKoin {
            androidContext(this@EvaluationDiiageKotlinApplication)
        }
    }
}