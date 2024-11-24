package com.example.controlwork.infrastructure.di

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.controlwork.infrastructure.db.LocationDao
import com.example.controlwork.models.location.Location
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
    lateinit var locationDao: LocationDao

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            DatabaseInitializer.initializeLocationsIfEmpty(this@App, locationDao)
        }
    }
}

object DatabaseInitializer {

    private const val LOCATIONS_JSON_FILE = "locations.json"

    suspend fun initializeLocationsIfEmpty(context: Context, locationDao: LocationDao) {
        val locationsCount = locationDao.getLocationsCount()

        if (locationsCount == 0) {
            val locations = loadLocationsFromJson(context, LOCATIONS_JSON_FILE)
            locationDao.upsertLocations(locations)
            Log.i("DatabaseInitializer", "Locations have been successfully inserted into the database.")
        }
    }

    fun loadLocationsFromJson(context: Context, fileName: String): List<Location> {
        val inputStream = context.assets.open(fileName)
        val reader = InputStreamReader(inputStream)
        val type = object : TypeToken<List<Map<String, Any>>>() {}.type
        val locationMaps: List<Map<String, Any>> = Gson().fromJson(reader, type)
        return locationMaps.map { map ->
            Location(
                id = (map["id"] as Double).toInt(),
                name = map["name"] as String,
                country = map["country"] as String
            )
        }
    }
}
