package com.example.controlwork.infrastructure.di

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.controlwork.infrastructure.db.CityDao
import com.example.controlwork.models.city.City
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStreamReader
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var cityDao: CityDao

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            DatabaseInitializer.initializeCitiesIfEmpty(this@App, cityDao)
        }
    }
}

object DatabaseInitializer {

    private const val CITIES_JSON_FILE = "cities.json"

    suspend fun initializeCitiesIfEmpty(context: Context, cityDao: CityDao) {
        val cityCount = cityDao.getCityCount()

        if (cityCount == 0) {
            val cities = loadCitiesFromJson(context, CITIES_JSON_FILE)
            cityDao.upsertCities(cities)
            Log.i("DatabaseInitializer", "Cities have been successfully inserted into the database.")
        }
    }

    private fun loadCitiesFromJson(context: Context, fileName: String): List<City> {
        val inputStream = context.assets.open(fileName)
        val reader = InputStreamReader(inputStream)
        val type = object : TypeToken<List<Map<String, Any>>>() {}.type
        val cityMaps: List<Map<String, Any>> = Gson().fromJson(reader, type)
        return cityMaps.map { map ->
            City(
                id = (map["id"] as Double).toInt(),
                name = map["name"] as String,
                country = map["country"] as String
            )
        }
    }
}
