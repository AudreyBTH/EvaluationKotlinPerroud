package com.example.evaluationkotlinperroud.anime.ui.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evaluationkotlinperroud.anime.data.remote.AnimeService
import com.example.evaluationkotlinperroud.anime.domain.model.Anime
import com.example.evaluationkotlinperroud.anime.domain.model.Title
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * État de l'écran Anime pour le pattern MVI.
 *
 * @property animes Liste des animes à afficher.
 * @property isLoading Indique si les données sont en cours de chargement.
 * @property error Message d'erreur s'il y a une exception lors du chargement.
 */

data class AnimeUiState(
    val animes: List<Anime> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)


/**
 * ViewModel pour l'écran Anime.
 *
 * Gère le chargement des animes depuis [AnimeService] et expose l'état via un [StateFlow].
 */
class AnimeViewModel() : ViewModel(), KoinComponent {

    /** Service API injecté via Koin */
    private val service: AnimeService by inject ()

    /** StateFlow exposant l'état de l'UI */
    private val _uiState = MutableStateFlow(AnimeUiState())
    val uiState: StateFlow<AnimeUiState> = _uiState.asStateFlow()

    init {
        loadAnimes()
    }

    /**
     * Charge la liste des animes depuis l'API.
     *
     * Met à jour [_uiState] avec les données chargées, l'état de chargement et les erreurs éventuelles.
     */
    private fun loadAnimes() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val response = service.animes()
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
                _uiState.update {
                    it.copy(isLoading = false, animes = mapped)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.update {
                    it.copy(isLoading = false, error = "Erreur : ${e.message}")
                }
            }
        }
    }
}
