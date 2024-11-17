package com.example.controlwork.infrastructure.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.controlwork.models.location.FollowedLocation

@Dao
interface FollowedLocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertFollowedLocation(followedLocation: FollowedLocation)

    @Query("SELECT * FROM followed_locations")
    fun getAllFollowedLocations(): LiveData<List<FollowedLocation>>

    @Query("DELETE FROM followed_locations WHERE id = :id")
    suspend fun deleteFollowedLocation(id: Int)
}