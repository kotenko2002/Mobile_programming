package com.example.controlwork.models.location

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "followed_locations",
    foreignKeys = [
        ForeignKey(
            entity = com.example.controlwork.models.city.City::class,
            parentColumns = ["id"],
            childColumns = ["cityId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["cityId"], name = "index_city_id")]
)
data class FollowedLocation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val cityId: Int
)