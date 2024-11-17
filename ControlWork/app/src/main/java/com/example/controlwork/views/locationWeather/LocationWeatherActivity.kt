package com.example.controlwork.views.locationWeather

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.controlwork.databinding.ActivityLocationWeatherBinding
import com.example.controlwork.modelViews.locationWeather.LocationWeatherViewModel
import com.example.controlwork.views.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationWeatherActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityLocationWeatherBinding
    private var _locationId: Int = 0
    private val _locationWeatherViewModel: LocationWeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityLocationWeatherBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        getLocationIdFromIntent()
        Toast.makeText(this, "Ти на сторінці $_locationId", Toast.LENGTH_LONG).show()

        _locationWeatherViewModel.getWeatherDataByLocationId(_locationId)
        observeWeatherData()

        setupButtonClickListener()
    }

    private fun getLocationIdFromIntent() {
        val intent = intent
        _locationId = intent.getIntExtra("LOCATION_ID", 0)
    }

    private fun observeWeatherData() {
        _locationWeatherViewModel.observeWeatherLiveData().observe(this) {
            value -> Log.d("TEST3", value.toString())
        }
    }

    private fun setupButtonClickListener() {
        _binding.btnGoToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}