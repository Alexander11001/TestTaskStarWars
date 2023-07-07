package com.example.testtaskstarwars.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlanetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val diameter: String,
    val population: String
)
