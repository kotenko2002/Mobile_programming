package com.example.lab6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.widget.Toast

class RingerModeReceiver(
    private val audioManager: AudioManager,
    private val listenSoundMode: () -> Boolean,
    private val listenVibrationMode: () -> Boolean
) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val mode = audioManager.ringerMode

        if (listenVibrationMode() && mode == AudioManager.RINGER_MODE_VIBRATE) {
            showToast(context, "Увімкнено режим вібрації \uD83D\uDD08")
        }

        if (listenSoundMode()) {
            when (mode) {
                AudioManager.RINGER_MODE_SILENT -> {
                    showToast(context, "Увімкнено беззвучний режим \uD83D\uDD07")
                }
                AudioManager.RINGER_MODE_NORMAL -> {
                    showToast(context, "Увімкнено звуковий режим \uD83D\uDD0A")
                }
            }
        }
    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
