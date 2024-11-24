package com.example.controlwork.models.location

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(
    tableName = "locations",
    indices = [Index(value = ["name"], name = "index_name", unique = false)]
)
data class Location(
    @PrimaryKey
    val id: Int,
    val name: String,
    val country: String
)
