package com.example.controlwork.infrastructure.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.controlwork.models.location.Location

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertLocations(locations: List<Location>)

    @Query("SELECT COUNT(*) FROM locations")
    suspend fun getLocationsCount(): Int

    @Query("SELECT * FROM locations WHERE name LIKE :prefix || '%' COLLATE NOCASE LIMIT 10")
    fun searchLocationsByName(prefix: String): LiveData<List<Location>>
}