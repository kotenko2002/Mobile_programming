package com.example.controlwork.views.locationWeather

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
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

        _locationWeatherViewModel.getWeatherDataByLocationId(_locationId)
        observeWeatherData()

        setupButtonsClickListeners()
    }

    private fun getLocationIdFromIntent() {
        val intent = intent
        _locationId = intent.getIntExtra("LOCATION_ID", 0)
    }

    @SuppressLint("SetTextI18n")
    private fun observeWeatherData() {
        _locationWeatherViewModel.observeWeatherLiveData().observe(this) { weatherData ->
            _binding.tvCityName.text = "City Name: ${weatherData.name}"
            _binding.tvTemperature.text = "Temperature: ${weatherData.main.temp}°C"
            _binding.tvWeatherDescription.text =
                "Description: ${weatherData.weather.joinToString(", ") { it.description }}"
            _binding.tvWindSpeed.text = "Wind Speed: ${weatherData.wind.speed} m/s"
            _binding.tvPressure.text = "Pressure: ${weatherData.main.pressure} hPa"
            _binding.tvHumidity.text = "Humidity: ${weatherData.main.humidity}%"
            _binding.tvVisibility.text = "Visibility: ${weatherData.visibility} m"
        }
    }

    private fun setupButtonsClickListeners() {
        _binding.btnGoToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        _binding.btnStopFollowing.setOnClickListener {
            _locationWeatherViewModel.stopFollowingLocation(_locationId)
            Toast.makeText(this, "Successfully stopped following location", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
