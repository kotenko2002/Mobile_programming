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

    @Query("SELECT * FROM cities")
    fun getAllCities(): LiveData<List<City>>
}