package com.example.evaluationkotlinperroud.system

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes

/**
 * Joue un son à partir d'une ressource audio.
 *
 * @param sound Identifiant de la ressource audio (dans le dossier raw).
 */

fun Context.playSound(@RawRes sound: Int) {
    val mediaPlayer = MediaPlayer.create(this, sound)
    mediaPlayer.start()
}