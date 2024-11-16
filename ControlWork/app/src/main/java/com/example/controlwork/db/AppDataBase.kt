package com.example.controlwork.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.controlwork.models.city.City

@Database(entities = [City::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}