package com.example.evaluationkotlinperroud.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.evaluationkotlinperroud.anime.ui.anime.AnimeScreen

/**
 * Définit les routes pour la navigation de l'application.
 *
 * @property route La chaîne représentant la route.
 */
sealed class AnimeRoutes(val route: String) {
    object AnimeScreen : AnimeRoutes("anime_screen")
}

/**
 * Hôte de navigation composable pour l'application.
 *
 * @param navController Contrôleur de navigation utilisé pour gérer la navigation entre les écrans.
 */
@Composable
fun AnimeNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = AnimeRoutes.AnimeScreen.route
    ) {
        composable(AnimeRoutes.AnimeScreen.route) {
            AnimeScreen(navController)
        }
    }
}
