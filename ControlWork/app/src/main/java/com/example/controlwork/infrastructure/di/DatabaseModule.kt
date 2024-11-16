package com.example.controlwork.infrastructure.di

import android.content.Context
import androidx.room.Room
import com.example.controlwork.infrastructure.db.AppDatabase
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
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCityDao(database: AppDatabase): CityDao {
        return database.cityDao()
    }
}