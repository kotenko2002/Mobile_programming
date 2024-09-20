package com.example.lab6

import android.content.Context
import android.content.IntentFilter
import android.media.AudioManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private lateinit var _audioManager: AudioManager
    private lateinit var _ringerModeReceiver: RingerModeReceiver
    private var _listenSoundMode: Boolean = true
    private var _listenVibrationMode: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        _binding.soundCheckBox.setOnCheckedChangeListener { _, isChecked ->
            _listenSoundMode = isChecked
        }
        _binding.vibrationCheckBox.setOnCheckedChangeListener { _, isChecked ->
            _listenVibrationMode = isChecked
        }

        _ringerModeReceiver = RingerModeReceiver(
            _audioManager,
            { _listenSoundMode },
            { _listenVibrationMode }
        )

        val filter = IntentFilter(AudioManager.RINGER_MODE_CHANGED_ACTION)
        registerReceiver(_ringerModeReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(_ringerModeReceiver)
    }
}
