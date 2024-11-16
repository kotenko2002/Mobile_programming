package com.example.controlwork.models.city

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(
    tableName = "cities",
    indices = [Index(value = ["name"], name = "index_name", unique = false)]
)
data class City(
    @PrimaryKey
    val id: Int,
    val name: String,
    val country: String
)
