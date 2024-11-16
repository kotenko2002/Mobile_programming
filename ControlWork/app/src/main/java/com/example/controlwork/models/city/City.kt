package com.example.controlwork.models.city

import com.example.controlwork.models.shared.Coord

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val state: String
)