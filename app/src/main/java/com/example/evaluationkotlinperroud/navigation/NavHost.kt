package com.example.evaluationkotlinperroud.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.evaluationkotlinperroud.anime.ui.anime.AnimeScreen
import com.example.evaluationkotlinperroud.anime.ui.anime.AnimeViewModel

// Routes pour la navigation
sealed class AnimeRoutes(val route: String) {
    object AnimeScreen : AnimeRoutes("anime_screen")
}

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
