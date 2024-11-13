package com.example.controlwork.retrofit

import com.example.controlwork.models.weatherData.WeatherData
import retrofit2.Call
import retrofit2.http.GET

interface WeatherApi {
    @GET("weather?q=London&appid=2426810b71e08dada14309acd9118fa4")
    fun getWeatherDataByCityId():Call<WeatherData>
}