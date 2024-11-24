package com.example.controlwork.models.location

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "followed_locations",
    foreignKeys = [
        ForeignKey(
            entity = Location::class,
            parentColumns = ["id"],
            childColumns = ["locationId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["locationId"], name = "index_location_id")]
)
data class FollowedLocation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val locationId: Int
)