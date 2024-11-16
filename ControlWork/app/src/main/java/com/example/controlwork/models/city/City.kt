package com.example.controlwork.models.city

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class City(
    @PrimaryKey
    val id: Int,
    val name: String,
    val country: String,
)