package com.example.controlwork.infrastructure.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.controlwork.models.city.City
import com.example.controlwork.models.location.FollowedLocation

@Database(entities = [City::class, FollowedLocation::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun followedLocationDao(): FollowedLocationDao
}