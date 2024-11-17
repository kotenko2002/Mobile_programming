package com.example.controlwork.infrastructure.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.controlwork.models.location.FollowedLocation
import com.example.controlwork.models.location.Location

@Dao
interface FollowedLocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertFollowedLocation(followedLocation: FollowedLocation)


    @Query("""
        SELECT locations.id, locations.name, locations.country 
        FROM followed_locations 
        INNER JOIN locations 
        ON followed_locations.locationId = locations.id
    """)
    fun getFollowedLocationsWithNames(): LiveData<List<Location>>

    @Query("DELETE FROM followed_locations WHERE locationId = :id")
    suspend fun deleteFollowedLocation(id: Int)
}