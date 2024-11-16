package com.example.controlwork.infrastructure.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.controlwork.models.city.City

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCity(city: City)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCities(cities: List<City>)

    @Query("SELECT * FROM cities")
    fun getAllCities(): LiveData<List<City>>

    @Query("SELECT COUNT(*) FROM cities")
    suspend fun getCityCount(): Int

    @Query("SELECT * FROM cities WHERE name LIKE :prefix || '%' COLLATE NOCASE LIMIT 10")
    fun searchCitiesByName(prefix: String): LiveData<List<City>>

}