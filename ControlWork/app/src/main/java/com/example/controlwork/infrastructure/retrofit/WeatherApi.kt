package com.example.controlwork.infrastructure.retrofit

import com.example.controlwork.models.weather.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    fun getWeatherDataByLocationId(
        @Query("id") locationId: Int
    ): Call<WeatherData>
}
