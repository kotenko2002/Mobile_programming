package com.example.controlwork.infrastructure.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.controlwork.models.location.Location
import com.example.controlwork.models.location.FollowedLocation

@Database(entities = [Location::class, FollowedLocation::class], version = 5, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
    abstract fun followedLocationDao(): FollowedLocationDao
}