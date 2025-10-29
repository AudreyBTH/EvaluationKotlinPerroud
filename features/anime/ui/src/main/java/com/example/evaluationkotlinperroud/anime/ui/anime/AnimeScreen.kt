package com.example.evaluationkotlinperroud.anime.ui.anime

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.evaluationkotlinperroud.anime.domain.model.Anime

@Composable
fun AnimeScreen(navController: NavController) {

    val animeViewModel: AnimeViewModel = viewModel()

    val animes = animeViewModel.animeList
    val isLoading = animeViewModel.isLoading.value
    val error = animeViewModel.errorMessage.value

    when {
        isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = error, color = MaterialTheme.colorScheme.error)
            }
        }
        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(animes) { anime ->
                    AnimeCard(anime)
                }
            }
        }
    }
}

@Composable
fun AnimeCard(anime: Anime) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            anime.imageUrl?.let {
                AsyncImage(
                    model = it,
                    contentDescription = anime.titles.firstOrNull()?.title,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(end = 12.dp)
                )
            }

            Column {
                Text(
                    text = anime.titles.firstOrNull()?.title ?: "Titre inconnu",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )

                anime.season?.let { Text("Saison : $it") }
                anime.year?.let { Text("Année : $it") }
                anime.episodes?.let { Text("Épisodes : $it") }
            }
        }
    }
}

