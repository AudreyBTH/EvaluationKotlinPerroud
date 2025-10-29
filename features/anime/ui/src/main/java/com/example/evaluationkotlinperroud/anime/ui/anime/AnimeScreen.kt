package com.example.evaluationkotlinperroud.anime.ui.anime

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.evaluationkotlinperroud.anime.domain.model.Anime
import com.example.evaluationkotlinperroud.anime.data.local.AnimeDao
import com.example.evaluationkotlinperroud.anime.data.local.AnimeEntity
import com.example.evaluationkotlinperroud.features.anime.ui.R

import com.example.evaluationkotlinperroud.system.playSound
import com.example.evaluationkotlinperroud.system.vibrate
import org.koin.compose.koinInject
import kotlinx.coroutines.launch

/**
 * Écran affichant la liste des animes.
 *
 * Récupère les données via le [AnimeViewModel] et gère l'état de chargement,
 * les erreurs et l'affichage des cartes d'anime.
 *
 * @param navController Contrôleur de navigation pour gérer la navigation entre écrans.
 */
@Composable
fun AnimeScreen(navController: NavController) {

    val animeViewModel: AnimeViewModel = viewModel()
    val uiState by animeViewModel.uiState.collectAsState()

    val dao: AnimeDao = koinInject()
    val scope = rememberCoroutineScope()

    var likedIds by remember { mutableStateOf<Set<Int>>(emptySet()) }
    LaunchedEffect(Unit) {
        likedIds = dao.getLikedIds().toSet()
    }

    when {
        uiState.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        uiState.error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = uiState.error!!, color = MaterialTheme.colorScheme.error)
            }
        }
        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(uiState.animes) { anime ->
                    val isLiked = likedIds.contains(anime.mal_id)
                    AnimeCard(
                        anime = anime,
                        isLiked = isLiked,
                        onToggleLike = {
                            scope.launch {
                                val nowLiked = !isLiked

                                val affected = dao.setLiked(anime.mal_id, nowLiked)
                                if (affected == 0) {
                                    dao.insertAnime(
                                        AnimeEntity(
                                            mal_id = anime.mal_id,
                                            title = anime.titles.firstOrNull()?.title ?: "Unknown",
                                            imageUrl = anime.imageUrl,
                                            season = anime.season,
                                            episodes = anime.episodes,
                                            rating = null,
                                            themes = null,
                                            liked = nowLiked
                                        )
                                    )
                                }
                                likedIds = if (nowLiked) likedIds + anime.mal_id else likedIds - anime.mal_id
                            }
                        }
                    )
                }
            }
        }
    }
}




/**
 * Carte affichant les informations d'un anime et permettant de le liker/deliker.
 *
 * @param anime Objet [Anime] contenant les informations de l'anime.
 * @param isLiked Indique si l'anime est actuellement liké.
 * @param onToggleLike Fonction appelée lorsque l'utilisateur clique sur le bouton like/unlike.
 */
@Composable
fun AnimeCard(
    anime: Anime,
    isLiked: Boolean,
    onToggleLike: () -> Unit
) {
    val context = LocalContext.current
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

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = anime.titles.firstOrNull()?.title ?: "Titre inconnu",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )

                anime.season?.let { Text(stringResource(R.string.saison)+ it) }
                anime.year?.let { Text(stringResource(R.string.ann_e)+ it) }
                anime.episodes?.let { Text(stringResource(R.string.pisodes)+ it) }
            }


            TextButton(onClick = {
                onToggleLike()
                context.vibrate()
                context.playSound(R.raw.nice)
            }) {
                Text(if (isLiked) stringResource(R.string.unlike) else stringResource(R.string.like))
            }
        }
    }
}
