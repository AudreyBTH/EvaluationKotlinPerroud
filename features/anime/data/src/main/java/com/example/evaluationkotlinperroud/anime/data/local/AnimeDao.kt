package com.example.evaluationkotlinperroud.anime.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Interface DAO (Data Access Object) pour accéder aux données des animes dans la base locale Room.
 */

@Dao
interface AnimeDao {
    /**
     * Insère un anime dans la base de données.
     * Remplace l’entrée existante en cas de conflit.
     *
     * @param anime L’entité [AnimeEntity] à insérer.
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: AnimeEntity)

    /**
     * Insère une liste d’animes dans la base de données.
     * Remplace les entrées existantes en cas de conflit.
     *
     * @param animes Liste des entités [AnimeEntity] à insérer.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimes(animes: List<AnimeEntity>)

    /**
     * Récupère tous les animes stockés dans la base de données.
     *
     * @return Une liste de [AnimeEntity].
     */
    @Query("SELECT * FROM animes")
    suspend fun getAllAnimes(): List<AnimeEntity>

    /**
     * Récupère un anime spécifique selon son identifiant.
     *
     * @param id Identifiant unique (mal_id) de l’anime.
     * @return L’objet [AnimeEntity] correspondant, ou null si non trouvé.
     */
    @Query("SELECT * FROM animes WHERE mal_id = :id")
    suspend fun getAnimeById(id: Int): AnimeEntity?

    /**
     * Met à jour l’état "aimé" (liked) d’un anime.
     *
     * @param id Identifiant de l’anime.
     * @param liked Nouvel état du champ liked (true ou false).
     * @return Le nombre de lignes affectées.
     */
    @Query("UPDATE animes SET liked = :liked WHERE mal_id = :id")
    suspend fun setLiked(id: Int, liked: Boolean): Int

    /**
     * Récupère la liste des identifiants des animes marqués comme "aimés".
     *
     * @return Liste des identifiants [Int] des animes aimés.
     */
    @Query("SELECT mal_id FROM animes WHERE liked = 1")
    suspend fun getLikedIds(): List<Int>
}
