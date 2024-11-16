package com.example.controlwork.infrastructure.di

import android.content.Context
import androidx.room.Room
import com.example.controlwork.infrastructure.db.AppDataBase
import com.example.controlwork.infrastructure.db.CityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDataBase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideCityDao(database: AppDataBase): CityDao {
        return database.cityDao()
    }
}