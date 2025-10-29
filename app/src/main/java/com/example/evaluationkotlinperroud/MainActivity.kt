package com.example.evaluationkotlinperroud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.evaluationkotlinperroud.navigation.AnimeNavHost
import com.example.evaluationkotlinperroud.ui.theme.EvaluationKotlinPerroudTheme

/**
 * Appel de la mainactivity qui lance l'application sur le paramétrage de la navigation
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EvaluationKotlinPerroudTheme {

                AnimeNavHost()

            }
        }
    }
}

