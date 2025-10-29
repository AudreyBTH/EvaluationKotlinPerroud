package com.example.evaluationkotlinperroud.anime.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Base de données Room pour stocker les informations des animes.
 *
 * @property entities Liste des entités utilisées dans la base (ici [AnimeEntity]).
 * @property version Version de la base de données (utilisée pour les migrations).
 * @property exportSchema Indique si le schéma doit être exporté.
 */

@Database(
    entities = [AnimeEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AnimeDatabase : RoomDatabase() {
    /**
     * Fournit l'accès au DAO [AnimeDao] pour interagir avec la table des animes.
     *
     * @return Une instance de [AnimeDao].
     */
    abstract fun animeDao(): AnimeDao
}

