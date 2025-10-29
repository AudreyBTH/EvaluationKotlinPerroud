package com.example.evaluationkotlinperroud.anime.ui.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.evaluationkotlinperroud.anime.data.remote.AnimeService
import com.example.evaluationkotlinperroud.anime.domain.model.Anime
import com.example.evaluationkotlinperroud.anime.domain.model.Title
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface AnimeContracts {

    data class UiState(
        val loading : Boolean
    )
}
class AnimeViewModel() : ViewModel(), KoinComponent {
    private val service: AnimeService by inject ()
    var animeList = mutableStateListOf<Anime>()
        private set

    var isLoading = mutableStateOf(false)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    init {
        loadAnimes()
    }

    private fun loadAnimes() {
        viewModelScope.launch {
            try {
                isLoading.value = true
                val response = service.animes() // getAnimes() -> animes()

                val mapped = response.map { dto ->
                    Anime(
                        mal_id = dto.mal_id,
                        titles = dto.titles.map { Title(it.type, it.title) },
                        imageUrl = dto.images?.jpg?.image_url,
                        season = dto.season,
                        year = dto.year,
                        episodes = dto.episodes
                    )
                }

                animeList.clear()
                animeList.addAll(mapped)

            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage.value = "Erreur : ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }
}
