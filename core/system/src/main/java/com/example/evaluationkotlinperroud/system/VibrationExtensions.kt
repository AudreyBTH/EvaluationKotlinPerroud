@file:Suppress("MissingPermission")
package com.example.evaluationkotlinperroud.system

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

/**
 * Fait vibrer l'appareil pendant une durée donnée.
 *
 * @param duration Durée de la vibration en millisecondes (par défaut 100 ms).
 * @param amplitude Intensité de la vibration (par défaut -1 pour automatique).
 *
 */

fun Context.vibrate(duration: Long = 100L, amplitude: Int = -1) {
    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrator.vibrate(VibrationEffect.createOneShot(duration, amplitude))
    } else {
        @Suppress("DEPRECATION")
        vibrator.vibrate(duration)
    }
}