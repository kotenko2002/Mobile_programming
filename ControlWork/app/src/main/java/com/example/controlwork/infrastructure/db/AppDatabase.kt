package com.example.controlwork.infrastructure.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.controlwork.models.city.City

@Database(entities = [City::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}